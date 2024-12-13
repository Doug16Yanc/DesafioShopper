package com.example.desafioshopper.model

import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDateTime

data class Driver(
    val id : Int? = 0,
    val name : String? = "",
    val description : String? = "",
    val vehicle : String? = "",
    val review : Review?,
    val value : Double? = 0.0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Review::class.java.classLoader),
        parcel.readValue(Double::class.java.classLoader) as? Double
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(vehicle)
        parcel.writeParcelable(review, flags)
        parcel.writeValue(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Driver> {
        override fun createFromParcel(parcel: Parcel): Driver {
            return Driver(parcel)
        }

        override fun newArray(size: Int): Array<Driver?> {
            return arrayOfNulls(size)
        }
    }
}