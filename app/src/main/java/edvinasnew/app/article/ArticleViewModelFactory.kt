package edvinasnew.app.article

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edvinasnew.app.news.NewsItem

class ArticleViewModelFactory(
    private val application: Application,
    private val article: NewsItem
) : ViewModelProvider.AndroidViewModelFactory(
    application
) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArticleViewModel(article) as T
    }
}