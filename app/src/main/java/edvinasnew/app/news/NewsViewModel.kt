package edvinasnew.app.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edvinasnew.app.utils.database.ArticleDao
import edvinasnew.app.utils.database.ArticleEntity
import edvinasnew.app.utils.formatDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.concurrent.thread

class NewsViewModel(

    private val service: NewsService,
    private val sourceId: String,
    private val articleDao: ArticleDao
    ): ViewModel() {
    private val _data = MutableLiveData<List<NewsItem>>()
    val data: LiveData<List<NewsItem>> get() = _data

    init {
//        service.getTopNewsFromSource(sourceId).enqueue(object : Callback<NewsListResponse> {
//            override fun onFailure(call: Call<NewsListResponse>, t: Throwable) {
//                t.printStackTrace()
//            }
//
//            override fun onResponse(
//                call: Call<NewsListResponse>,
//                response: Response<NewsListResponse>
//            ) {
//                response.body()!!.articles
//                    .map { NewsItem(it.urlToImage, it.title, it.description, it.publishedAt) }
//                    .let { _data.postValue(it) }
//            }
//
//        })
        this.onPopularTodayArticlesSelected()
    }

    fun onAllTimeArticlesSelected() {
        service
            .getPopularTodayFromSource(
                sourceId,
                formatDate(
                    Date(
                        Calendar.getInstance().apply {
                            time = Date()
                            add(Calendar.DAY_OF_YEAR, -10)
                        }.timeInMillis
                    )
                ),
                formatDate(
                    Date(
                        Calendar.getInstance().apply {
                            time = Date()
                            add(Calendar.DAY_OF_YEAR, -5)
                        }.timeInMillis
                    )
                )
            )
            .enqueue(object : Callback<NewsListResponse> {
                override fun onFailure(call: Call<NewsListResponse>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<NewsListResponse>,
                    response: Response<NewsListResponse>
                ) {
                    thread {
                        response.body()!!.articles
                            .map {
                                NewsItem(
                                    it.urlToImage,
                                    it.title,
                                    it.description,
                                    it.publishedAt,
                                    it.author,
                                    it.url
                                    //it.sourceid
                                )
                            }
                            .map {
                                ArticleEntity(
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
                            .also { articleDao.deleteAll(sourceId, 1) }
                            .also { articleDao.insert(it) }
                            .let { articleDao.query(sourceId, 1) }
                            .map {
                                NewsItem(
                                    it.urlToImage!!,
                                    it.title!!,
                                    it.description!!,
                                    it.publishedAt,
                                    it.author!!,
                                    it.url,
                                    it.favorite
                                    //it.sourceId
                                )
                            }
                            .let { _data.postValue(it) }
                    }
                }
            })
    }

    fun onNewestArticlesSelected() {
        service
            .getNewestFromSource(
                sourceId,
                formatDate(Date())
            )
            .enqueue(object : Callback<NewsListResponse> {
                override fun onFailure(call: Call<NewsListResponse>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<NewsListResponse>,
                    response: Response<NewsListResponse>
                ) {
                    response.body()!!.articles
                        .map {
                            NewsItem(
                                it.urlToImage,
                                it.title,
                                it.description,
                                it.publishedAt,
                                it.author,
                                it.url//,
                                //it.favorite,
                                //it.sourceid
                            )
                        }
                        .let { _data.postValue(it) }
                }
            })
    }

    fun onPopularTodayArticlesSelected() {
        service
            .getTopNewsFromSource(sourceId)
            .enqueue(object : Callback<NewsListResponse> {
                override fun onFailure(call: Call<NewsListResponse>, t: Throwable) {
                    t.printStackTrace()
                }
                override fun onResponse(
                    call: Call<NewsListResponse>,
                    response: Response<NewsListResponse>
                ) {
                    thread {
                        response.body()!!.articles
                            .map {
                                NewsItem(
                                    it.urlToImage,
                                    it.title,
                                    it.description,
                                    it.publishedAt,
                                    it.author,
                                    it.url
                                    //it.sourceid

                                )
                            }
                            .map {
                                ArticleEntity(
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
                            .also { articleDao.deleteAll(sourceId, 1) }
                            .also { articleDao.insert(it) }
                            .let { articleDao.query(sourceId, 1) }
                            .map {
                                NewsItem(
                                    it.urlToImage!!,
                                    it.title!!,
                                    it.description!!,
                                    it.publishedAt,
                                    it.author!!,
                                    it.url,
                                    it.favorite
                                    //it.sourceId
                                )
                            }
                            .let { _data.postValue(it) }
                    }
                }
            })
    }

    fun changeArticleFavoriteStatus(article: NewsItem) {
        thread {
            articleDao.changeFavoriteStatus(article.url)
            //getArticlesFromDB(chipid.value!!)
            this.onPopularTodayArticlesSelected()
        }
    }

}