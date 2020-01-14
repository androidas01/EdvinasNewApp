package edvinasnew.app.news

data class NewsListResponse (
    val status: String,
    val totalResults: Int,
    val articles: List<NewsArticles>
)

class NewsArticles (
    val source: ArticleSource,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

class ArticleSource (
    val id: String?,
    val name: String
)




