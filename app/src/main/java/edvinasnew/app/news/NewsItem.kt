package edvinasnew.app.news

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsItem(
    val urlToImage: String,
    val title: String,
    val description: String,
    val date: String,
    val author: String,
    val url: String,
    val favorite: Boolean = false
    // val sourceid: String
) : Parcelable