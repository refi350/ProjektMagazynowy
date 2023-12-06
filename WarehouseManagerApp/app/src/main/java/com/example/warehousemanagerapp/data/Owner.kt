package com.example.warehousemanagerapp.data

import android.os.Parcel
import android.os.Parcelable
import com.example.warehousemanagerapp.util.JsonConst
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Owner (
    @SerializedName(JsonConst.OWNER_ID)
    @Expose
    var ownerId: Int? = null,

    @SerializedName(JsonConst.OWNER_NAME)
    @Expose
    var ownerName: String? = null,

    @SerializedName(JsonConst.EMAIL)
    @Expose
    var email: String? = null
): Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readValue(Int::class.java.classLoader) as? Int,
//        parcel.readString(),
//        parcel.readString()
//    ) {
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeValue(ownerId)
//        parcel.writeString(ownerName)
//        parcel.writeString(email)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Owner> {
//        override fun createFromParcel(parcel: Parcel): Owner {
//            return Owner(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Owner?> {
//            return arrayOfNulls(size)
//        }
//    }

}