package apwdevs.football.club

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataListFC(val name: String, val image: Int) : Parcelable