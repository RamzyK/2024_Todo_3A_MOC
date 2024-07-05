package com.example.myfirstapp.model

import android.os.Parcel
import android.os.Parcelable

data class TodoModel(
    val title: String?,
    val description: String?,
    val date: String?,
    val isChecked: Boolean
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(date)
        parcel.writeByte(if (isChecked) 1 else 0)
    }

    companion object CREATOR : Parcelable.Creator<TodoModel> {
        override fun createFromParcel(parcel: Parcel): TodoModel {
            return TodoModel(parcel)
        }

        override fun newArray(size: Int): Array<TodoModel?> {
            return arrayOf()
        }
    }
}
