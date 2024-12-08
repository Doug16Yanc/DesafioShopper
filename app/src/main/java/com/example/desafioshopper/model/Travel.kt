package com.example.desafioshopper.model

import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class Travel(
    val dateTime : LocalDateTime? = now(),
    val nameDriver : String? = "",
    val origin : String? = "",
    val destination : String? = "",
    val distance : Double? = 0.0,
    val time : Double? = 0.0,
    val value : Double? = 0.0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("dateTime"),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nameDriver)
        parcel.writeString(origin)
        parcel.writeString(destination)
        parcel.writeValue(distance)
        parcel.writeValue(time)
        parcel.writeValue(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Travel> {
        override fun createFromParcel(parcel: Parcel): Travel {
            return Travel(parcel)
        }

        override fun newArray(size: Int): Array<Travel?> {
            return arrayOfNulls(size)
        }
    }
}
