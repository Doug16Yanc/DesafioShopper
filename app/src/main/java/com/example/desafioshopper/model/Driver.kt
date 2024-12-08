package com.example.desafioshopper.model

import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDateTime

data class Driver(
    val name : String? = "",
    val description : String? = "",
    val vehicle : String? = "",
    val rate : Double? = 0.0,
    val value : Double? = 0.0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(vehicle)
        parcel.writeValue(rate)
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
