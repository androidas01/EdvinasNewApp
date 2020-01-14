package edvinasnew.app.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edvinasnew.app.source.Source
import edvinasnew.app.source.SourceListResponse
import edvinasnew.app.source.SourceService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel(
    val service: NewsService
): ViewModel() {
    private val _data = MutableLiveData<List<SourceArticle>>()
    val data: LiveData<List<SourceArticle>> get() = _data

    init {
        service.getSources().enqueue(object : Callback<NewsListResponse> {
            override fun onFailure(call: Call<NewsListResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<NewsListResponse>,
                response: Response<NewsListResponse>
            ) {
                response.body()!!.articles
                    .map { SourceArticle(it.title, it.description, it.urlToImage, it.publishedAt, it.source.id) }
                    .let { _data.postValue(it) }
            }

        })
    }
}