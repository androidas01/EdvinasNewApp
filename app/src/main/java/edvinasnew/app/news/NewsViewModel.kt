package edvinasnew.app.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edvinasnew.app.utils.database.ArticleDao
import edvinasnew.app.utils.database.ArticleEntity
import edvinasnew.app.utils.formatDate
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.concurrent.thread

const val CHIP_ID_TODAY = 1

class NewsViewModel(
    private val service: NewsService,
    private val sourceId: String,
    private val articleDao: ArticleDao
) : ViewModel() {
    private val disposables = CompositeDisposable()
    private val _data = MutableLiveData<List<NewsItem>>()
    val data: LiveData<List<NewsItem>> get() = _data

    init {
        this.onPopularTodayArticlesSelected()
    }

    fun toItem(it: ArticleResponse): NewsItem {
        return NewsItem(
            it.urlToImage,
            it.title,
            it.description,
            it.publishedAt,
            it.author,
            it.url
        )
    }

    fun toEntity(it: NewsItem): ArticleEntity {
        return ArticleEntity(
            sourceId = sourceId,
            chipId = 1,
            author = it.author,
            title = it.title,
            description = it.description,
            url = it.url,
            urlToImage = it.urlToImage,
            publishedAt = it.date,
            favorite = false
        )
    }

    fun toItem(it: ArticleEntity): NewsItem {
        return NewsItem(
            it.urlToImage!!,
            it.title!!,
            it.description!!,
            it.publishedAt,
            it.author!!,
            it.url,
            it.favorite
            // it.sourceId
        )
    }

    fun onAllTimeArticlesSelected() {
        val disposable = service
            .getNews(sourceId)
            .map { response -> response.articles }
            .map { articles -> articles.map(::toItem) }
            .map { items -> items.map{this.toEntity(it)} }
            .flatMapCompletable { entities ->
                articleDao.deleteAll(sourceId, CHIP_ID_TODAY)
                    .andThen(articleDao.insert(entities))
            }
            .andThen(articleDao.query(sourceId, CHIP_ID_TODAY))
            .map { entities -> entities.map(::toItem) }
            .subscribe(
                { items -> _data.postValue(items) },
                { it.printStackTrace() }
            )

        disposables.add(disposable)
//            .enqueue(object : Callback<NewsListResponse> {
//                override fun onFailure(call: Call<NewsListResponse>, t: Throwable) {
//                    t.printStackTrace()
//                }

//                override fun onResponse(
//                    call: Call<NewsListResponse>,
//                    response: Response<NewsListResponse>
//                ) {
//                    thread {
//                        response.body()!!.articles
//                            .map {
//                                NewsItem(
//                                    it.urlToImage,
//                                    it.title,
//                                    it.description,
//                                    it.publishedAt,
//                                    it.author,
//                                    it.url
//                                    // it.sourceid
//                                )
//                            }
//                            .map {
//                                ArticleEntity(
//                                    sourceId = sourceId,
//                                    chipId = 1,
//                                    author = it.author,
//                                    title = it.title,
//                                    description = it.description,
//                                    url = it.url,
//                                    urlToImage = it.urlToImage,
//                                    publishedAt = it.date,
//                                    favorite = false
//                                )
//                            }
//                            .also { articleDao.deleteAll(sourceId, CHIP_ID_TODAY) }
//                            .also { articleDao.insert(it) }
//                            .let { articleDao.query(sourceId, CHIP_ID_TODAY) }
//                            .map {
//                                NewsItem(
//                                    it.urlToImage!!,
//                                    it.title!!,
//                                    it.description!!,
//                                    it.publishedAt,
//                                    it.author!!,
//                                    it.url,
//                                    it.favorite
//                                    // it.sourceId
//                                )
//                            }
//                            .let { _data.postValue(it) }
//                    }
//                }
//            })
        //}
    }

    fun onNewestArticlesSelected() {
        val disposable = service
            .getNewestFromSource(
                sourceId,
                formatDate(Date())
            )
            .map { response -> response.articles }
            .map{ articles -> articles.map(::toItem) }
            .map{ items -> items.map{this.toEntity(it)}}
            .flatMapCompletable { entities ->
                articleDao.deleteAll(sourceId, CHIP_ID_TODAY)
                    .andThen(articleDao.insert(entities))
            }
            .andThen(articleDao.query(sourceId, CHIP_ID_TODAY))
            .map { entities -> entities.map(::toItem) }
            .subscribe(
                { items -> _data.postValue(items) },
                { it.printStackTrace() }
            )

        disposables.add(disposable)


//            .enqueue(object : Callback<NewsListResponse> {
//                override fun onFailure(call: Call<NewsListResponse>, t: Throwable) {
//                    t.printStackTrace()
//                }
//
//                override fun onResponse(
//                    call: Call<NewsListResponse>,
//                    response: Response<NewsListResponse>
//                ) {
//                    response.body()!!.articles
//                        .map {
//                            NewsItem(
//                                it.urlToImage,
//                                it.title,
//                                it.description,
//                                it.publishedAt,
//                                it.author,
//                                it.url
//                                // it.favorite,
//                                // it.sourceid
//                            )
//                        }
//                        .let { _data.postValue(it) }
//                }
//            })
    }



    fun onPopularTodayArticlesSelected() {
        val disposable = service
         .getTopNewsFromSource(sourceId)
//            .enqueue(object : Callback<NewsListResponse> {
//                override fun onFailure(call: Call<NewsListResponse>, t: Throwable) {
//                    t.printStackTrace()
//                }

            .map { response -> response.articles }
            .map { articles -> articles.map ( ::toItem ) }
            .map { items -> items.map { this.toEntity(it) } }
            .flatMapCompletable { entities ->  articleDao.deleteAll(sourceId, CHIP_ID_TODAY )
                .andThen(articleDao.insert(entities)) }
            .andThen(articleDao.query(sourceId, CHIP_ID_TODAY))
            .map { entities -> entities.map(::toItem) }
            .subscribe(
                { items ->  _data.postValue(items) },
                { it.printStackTrace() }
            )

        disposables.add(disposable)

//        override fun onResponse(
//                    call: Call<NewsListResponse>,
//                    response: Response<NewsListResponse>
//                ) {
//                    thread {
//                        response.body()!!.articles
//                            .map(::toItem)
//                            .map(::toEntity)
//                            .also { articleDao.deleteAll(sourceId, CHIP_ID_TODAY) }
//                            .also { articleDao.insert(it) }
//                            .let { articleDao.query(sourceId, CHIP_ID_TODAY) }
//                            .map {
//                                toItem(it)
//                            }
//                            .let { _data.postValue(it) }
//                    }
//                }
//            })
    }

    fun changeArticleFavoriteStatus(article: NewsItem) {
        thread {
            articleDao.changeFavoriteStatus(article.url)
            // getArticlesFromDB(chipid.value!!)
            this.onPopularTodayArticlesSelected()
        }
    }
}