package edvinasnew.app.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edvinasnew.app.utils.database.SourceDao
import edvinasnew.app.utils.database.SourceEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class SourceViewModel(
        service: SourceService,
        private val sourceDao: SourceDao
    ): ViewModel() {

    private val _data = MutableLiveData<List<SourceItem>>()
    val data: LiveData<List<SourceItem>> get() = _data

    init {
        thread {
            sourceDao.query().map { SourceItem(it.title, it.description, id = it.id) }
                .let { _data.postValue(it) }
        }

        service.getSources().enqueue(object : Callback<SourceListResponse> {
            override fun onFailure(call: Call<SourceListResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<SourceListResponse>,
                response: Response<SourceListResponse>
            ) {
                thread {
                    response.body()!!.sources
                        .map { SourceItem(it.name, it.description, it.id) }
                        .map { SourceEntity(it.id, it.title, it.description) }
                        .also { sourceDao.insert(it) }
                        .let { sourceDao.query() }
                        .map { SourceItem(title = it.title, description = it.description, id = it.id) }
                        .let { _data.postValue(it) }
                }
            }
        })
    }

    private var sortbool = false

    fun sort(): Boolean {

        sortbool = !sortbool
        _data.postValue(
            when (sortbool) {
                false ->
                    (_data.value ?: listOf()).sortedBy { it.title }
                else ->
                    (_data.value ?: listOf()).sortedByDescending { it.title }
            }
        )

        return sortbool
    }
}
