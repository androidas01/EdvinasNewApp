package edvinasnew.app.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edvinasnew.app.news.ArticleResponse
import edvinasnew.app.news.NewsItem
import edvinasnew.app.utils.database.SourceDao
import edvinasnew.app.utils.database.SourceEntity
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class SourceViewModel(private val service: SourceService, private val sourceDao: SourceDao) : ViewModel() {

    private val _data = MutableLiveData<List<SourceItem>>()
    val data: LiveData<List<SourceItem>> get() = _data

    val publishSubject = PublishSubject.create<String>();

    private val disposables = CompositeDisposable()
    var sortID = MutableLiveData(true)
    val sortid: LiveData<Boolean> get() = sortID

    init {
//        thread {
//            sourceDao.query().map { SourceItem(it.title, it.description, id = it.id) }
//                .let { _data.postValue(it) }
//        }



        val disposable = service.getSources()
            .map { response ->  response.sources }
            .map { sources -> sources.map(::toItem) }
            .map { items -> items.map{this.toEntity(it)} }
            .flatMapCompletable { entities ->
                sourceDao.insert(entities)
            }
            .andThen(sourceDao.query())
            .map { entities -> entities.map(::toItem)  }
            .subscribe(
                { items -> _data.postValue(items) },
                { it.printStackTrace() }
            )

        disposables.add(disposable)
//        service.getSources().enqueue(object : Callback<SourceListResponse> {
//            override fun onFailure(call: Call<SourceListResponse>, t: Throwable) {
//                t.printStackTrace()
//            }
//
//            override fun onResponse(
//                call: Call<SourceListResponse>,
//                response: Response<SourceListResponse>
//            ) {
//                thread {
//                    response.body()!!.sources
//                        .map { toItem(it) }
//                        .map {
//                            toEntity(it)
//                        }
//                        .also { sourceDao.insert(it) }
//                        .let { sourceDao.query() }
//                        .map { toItem(it) }
//                        .let { _data.postValue(it) }
//                }
//            }
//        })
        val publishDisposable = publishSubject.filter { it.length > 2 }
            .debounce(200, TimeUnit.MILLISECONDS)
            .flatMapSingle { query ->
                sourceDao.getSourcesBySearch(query)
                    .subscribeOn(Schedulers.io())
            }
            .subscribe { it ->
                postArticleListToData(it.map { SourceItem(it.id, it.title, it.description) })
            }
    }

    private fun toItem(it: SourceEntity) =
        SourceItem(title = it.title, description = it.description, id = it.id)

    private fun toItem(it: SourceResponse) =
        SourceItem(it.name, it.description, it.id)

    private fun toEntity(it: SourceItem) =
        SourceEntity(it.id, it.title, it.description)

    private var sortbool = false

    fun sort(): Boolean {

        sortbool = !sortbool
        _data.postValue(if (sortbool == false) (_data.value ?: listOf()).sortedBy { it.title }
        else (_data.value ?: listOf()).sortedByDescending { it.title })

        return sortbool
    }

    fun onCreate() {
//        thread {
//            if (sortid.value == true) {
//                sourceDao.query()
//                    .map { SourceItem(it.id, it.title, it.description) }
//                    .let { _data.postValue(it) }
//            } else {
//                sourceDao.queryDESC()
//                    .map { SourceItem(it.id, it.title, it.description) }
//                    .let { _data.postValue(it) }
//            }
//        }

        service.getSources()
            .map { response ->  response.sources }
            .map { sources -> sources.map(::toItem) }
            .map { items -> items.map{this.toEntity(it)} }
            .flatMapCompletable { entities ->
                sourceDao.insert(entities)
            }
            .andThen(
                (if (sortID.value == true) {
                    this.let { sourceDao.query() }
                } else {
                    this.let { sourceDao.queryDESC() }
                })
            )
            .map { entities -> entities.map(::toItem)  }
            .subscribe(
                { items -> _data.postValue(items) },
                { it.printStackTrace() }
            )




//        service
//            .getSources()
//            .enqueue(object : Callback<SourceListResponse> {
//                override fun onFailure(call: Call<SourceListResponse>, t: Throwable) {
//                    t.printStackTrace()
//                }
//
//                override fun onResponse(
//                    call: Call<SourceListResponse>,
//                    response: Response<SourceListResponse>
//                ) {
//                    thread {
//                        response.body()!!.sources
//                            .map { SourceItem(it.id, it.name, it.description) }
//                            .map { SourceEntity(it.id, it.title, it.description) }
//                            .also { sourceDao.insert(it) }
//                        (if (sortID.value == true) {
//                            this.let { sourceDao.query() }
//                        } else {
//                            this.let { sourceDao.queryDESC() }
//                        })
//                            .map { SourceItem(it.id, it.title, it.description) }
//                            .let { _data.postValue(it) }
//                    }
//                }
//            })
    }

    fun onSearch(searchText: String) {
        //        val disposable =sourceDao.getSourcesBySearch(searchText)
//            .subscribeOn(Schedulers.io())
//            .subscribe { it ->
//                postArticleListToData(it.map { SourceItem(it.id, it.title, it.description) })
//            }
//            disposables.add(disposable)
        Log.d("",searchText)
        publishSubject.onNext(searchText)
    }

    private fun postArticleListToData(sourceList: List<SourceItem>) {
        _data.postValue(sourceList)
    }

}
