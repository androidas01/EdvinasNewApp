package edvinasnew.app.source

import retrofit2.Call
import retrofit2.http.GET

interface SourceService {
    @GET("/v2/sources?apiKey=212aa54440134caa9a597961e8a8e8c3")
    fun getSources(): Call<SourceListResponse>
}