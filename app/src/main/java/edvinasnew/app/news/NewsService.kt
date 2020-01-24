package edvinasnew.app.news

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

//    @GET("/v2/everything?q=bitcoin&from=2019-12-14&sortBy=publishedAt&apiKey=57a79eac5a8f44efa2bd3408139b83f3")
//    fun getSources(): Call<NewsListResponse>

    @GET("/v2/top-headlines?apiKey=212aa54440134caa9a597961e8a8e8c3")
    fun getTopNewsFromSource(@Query("sources") sourceId: String): Call<NewsListResponse>

    @GET("/v2/everything?apiKey=212aa54440134caa9a597961e8a8e8c3")
    fun getPopularTodayFromSource(
        @Query("sources") sourceId: String,
        @Query("from") fromDate: String,
        @Query("to") toDate: String
    ): Call<NewsListResponse>

    @GET("/v2/everything")
    fun getNewestFromSource(
        @Query("sources") sourceId: String,
        @Query("from") fromDate: String
    ): Call<NewsListResponse>


}