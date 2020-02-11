package edvinasnew.app.news

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edvinasnew.app.BuildConfig
import edvinasnew.app.utils.database.NewsDatabase
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsViewModelFactory(
    private val application: Application,
    private val sourceId: String
) : ViewModelProvider.AndroidViewModelFactory(
    application
) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.SERVER)//            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

        val service = retrofit.create(NewsService::class.java)

        return NewsViewModel(
            service, sourceId,
            NewsDatabase.getInstance(application).articleDao
        ) as T
    }
}