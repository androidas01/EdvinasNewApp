package edvinasnew.app

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TutorialScreenConfig(
    val tutorialText: String,
    val tutorialImage: Int
) : Parcelable


