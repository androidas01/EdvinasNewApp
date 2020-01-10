package edvinasnew.app

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SourceArticle(
    val title: String,
    val description: String,
    val image: Int,
    val date: String
) : Parcelable