package edvinasnew.app.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edvinasnew.app.news.NewsItem
import edvinasnew.app.utils.database.ArticleDao
import kotlin.concurrent.thread

class FavoriteViewModel(
    // private val service: NewsService,
    private val articleDao: ArticleDao

) : ViewModel() {
    private val _data = MutableLiveData<List<NewsItem>>()
    val data: LiveData<List<NewsItem>> get() = _data

    fun onCreate() {
        getArticlesFromDB()
    }

    private fun getArticlesFromDB() {
        thread {
            articleDao.getFavorite()
                .map {
                    NewsItem(
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
                .let { _data.postValue(it) }
        }
    }

    fun changeArticleFavoriteStatus(article: NewsItem) {
        thread {
            articleDao.changeFavoriteStatus(article.url)
            getArticlesFromDB()
        }
    }
}