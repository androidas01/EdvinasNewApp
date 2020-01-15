package edvinasnew.app.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edvinasnew.app.news.NewsItem

class ArticleViewModel(
    private val article: NewsItem
) : ViewModel() {

    private val _data = MutableLiveData<NewsItem>()
    val data: LiveData<NewsItem> get() = _data

    init {
        _data.postValue(article)
    }

}