package edvinasnew.app.news

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SourceArticle(
    val title: String,
    val description: String,
    val image: String,
    val date: String,
    val id: String?
) : Parcelable