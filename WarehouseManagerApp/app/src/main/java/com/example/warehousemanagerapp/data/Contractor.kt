package com.example.warehousemanagerapp.data

import android.os.Parcel
import android.os.Parcelable
import com.example.warehousemanagerapp.util.JsonConst
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Contractor(
    @SerializedName(JsonConst.CONTRACTOR_ID)
    @Expose
    var contractorId: Int? = null,

    @SerializedName(JsonConst.CONTRACTOR_NAME)
    @Expose
    var contractorName: String? = null,

    @SerializedName(JsonConst.CONTRACTOR_ADDRESS)
    @Expose
    var contractorAddress: String? = null,

    @SerializedName(JsonConst.RECIPIENT)
    @Expose
    var recipient: Boolean? = null,

    @SerializedName(JsonConst.SUPPLIER)
    @Expose
    var supplier: Boolean? = null,

    @SerializedName(JsonConst.NIP)
    @Expose
    var nip: String? = null
): Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(contractorId)
        parcel.writeString(contractorName)
        parcel.writeString(contractorAddress)
        parcel.writeValue(recipient)
        parcel.writeValue(supplier)
        parcel.writeString(nip)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Contractor> {
        override fun createFromParcel(parcel: Parcel): Contractor {
            return Contractor(parcel)
        }

        override fun newArray(size: Int): Array<Contractor?> {
            return arrayOfNulls(size)
        }
    }

}
