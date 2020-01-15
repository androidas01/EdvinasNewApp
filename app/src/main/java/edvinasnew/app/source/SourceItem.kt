package edvinasnew.app.source

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//@Parcelize
data class SourceItem(
    val title: String,
    val description: String,
    val id: String
) //: Parcelable