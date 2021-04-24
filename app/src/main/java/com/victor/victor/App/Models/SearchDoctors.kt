package com.victor.victor.App.Models

import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.IdRes

data class SearchDoctors
constructor(var doctorsName:String?,
            var doctorsOccupations:String?,
            var doctorsNumber:String?,
            var doctorsImage:Int,
            var time:String?,
            var rating:String?,
            var about:String?
            ):Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(doctorsName)
        parcel.writeString(doctorsOccupations)
        parcel.writeString(doctorsNumber)
        parcel.writeInt(doctorsImage)
        parcel.writeString(time)
        parcel.writeString(rating)
        parcel.writeString(about)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchDoctors> {
        override fun createFromParcel(parcel: Parcel): SearchDoctors {
            return SearchDoctors(parcel)
        }

        override fun newArray(size: Int): Array<SearchDoctors?> {
            return arrayOfNulls(size)
        }
    }


}