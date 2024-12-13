package com.example.desafioshopper.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter.*

data class Travel(
    val id: Int? = 0,
    val dateTime: LocalDateTime? = now(),
    val nameDriver: String? = "",
    val origin: String? = "",
    val destination: String? = "",
    val distance: Double? = 0.0,
    val duration: String? = "",
) : Parcelable {
    @SuppressLint("NewApi")
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()?.let {
            LocalDateTime.parse(it, ISO_DATE_TIME)
        },
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString()
    )

    @SuppressLint("NewApi")
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id ?: 0)
        parcel.writeString(dateTime?.format(ISO_DATE_TIME))
        parcel.writeString(nameDriver)
        parcel.writeString(origin)
        parcel.writeString(destination)
        parcel.writeValue(distance)
        parcel.writeString(duration)
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
