package com.example.warehousemanagerapp.data

import android.os.Parcel
import android.os.Parcelable
import com.example.warehousemanagerapp.util.JsonConst
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Address(
    @SerializedName(JsonConst.ADDRESS_ID)
    @Expose
    var addressId: Int? = null,

    @SerializedName(JsonConst.STREET_NAME)
    @Expose
    var streetName: String? = null,

    @SerializedName(JsonConst.HOUSE_NUMBER)
    @Expose
    var houseNumber: String? = null,

    @SerializedName(JsonConst.LOCAL_NUMBER)
    @Expose
    var localNumber: Int? = null,

    @SerializedName(JsonConst.PLACE)
    @Expose
    var place: String? = null,

    @SerializedName(JsonConst.CODE)
    @Expose
    var code: String? = null
): Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(addressId)
        parcel.writeString(streetName)
        parcel.writeString(houseNumber)
        parcel.writeValue(localNumber)
        parcel.writeString(place)
        parcel.writeString(code)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Address> {
        override fun createFromParcel(parcel: Parcel): Address {
            return Address(parcel)
        }

        override fun newArray(size: Int): Array<Address?> {
            return arrayOfNulls(size)
        }
    }
}