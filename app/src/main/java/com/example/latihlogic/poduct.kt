package com.example.latihlogic

import android.os.Parcel
import android.os.Parcelable

data class product(val nama: String?, val imgRes:Int, val harganya:Int):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nama)
        parcel.writeInt(imgRes)
        parcel.writeInt(harganya)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<product> {
        override fun createFromParcel(parcel: Parcel): product {
            return product(parcel)
        }

        override fun newArray(size: Int): Array<product?> {
            return arrayOfNulls(size)
        }
    }
}
