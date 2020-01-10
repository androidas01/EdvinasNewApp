package edvinasnew.app

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Source(
    val title: String,
    val description: String
) : Parcelable