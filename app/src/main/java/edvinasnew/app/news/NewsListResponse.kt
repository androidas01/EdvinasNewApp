package edvinasnew.app.news
data class NewsListResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleResponse>
)

data class ArticleResponse(
    val source: ArticleSourceResponse,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String?
    // val favorite: Boolean,
    // val sourceid: String
)

data class ArticleSourceResponse(
    val id: String?,
    val name: String
)