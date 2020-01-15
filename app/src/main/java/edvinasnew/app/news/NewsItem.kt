package edvinasnew.app.news

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsItem(
    val urlToImage: String,
    val title: String,
    val description: String,
    val date: String
) : Parcelable