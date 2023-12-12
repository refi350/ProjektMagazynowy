package com.example.warehousemanagerapp.data

import android.os.Parcel
import android.os.Parcelable
import com.example.warehousemanagerapp.util.JsonConst
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Contractor(
    @SerializedName(JsonConst.CONTRACTOR_ID)
    @Expose
    var contractorId: Int? = null,

    @SerializedName(JsonConst.CONTRACTOR_NAME)
    @Expose
    var contractorName: String? = null,

    @SerializedName(JsonConst.CONTRACTOR_ADDRESS)
    @Expose
    var contractorAddress: Address? = null,

    @SerializedName(JsonConst.RECIPIENT)
    @Expose
    var recipient: Boolean? = null,

    @SerializedName(JsonConst.SUPPLIER)
    @Expose
    var supplier: Boolean? = null,

    @SerializedName(JsonConst.NIP)
    @Expose
    var nip: String? = null
): Parcelable
//    constructor(parcel: Parcel) : this(
//        parcel.readValue(Int::class.java.classLoader) as? Int,
//        parcel.readString(),
//        parcel.readValue(Address::class.java.classLoader) as? Address,
//        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
//        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
//        parcel.readString()
//    )
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeValue(contractorId)
//        parcel.writeString(contractorName)
//        parcel.writeValue(contractorAddress)
//        parcel.writeValue(recipient)
//        parcel.writeValue(supplier)
//        parcel.writeString(nip)
//    }
//
//    override fun describeContents(): Int = 0
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as Contractor
//
//        if (contractorId != other.contractorId) return false
//        if (contractorName != other.contractorName) return false
//        if (contractorAddress != other.contractorAddress) return false
//        if (recipient != other.recipient) return false
//        if (supplier != other.supplier) return false
//        if (nip != other.nip) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        var result = contractorId ?: 0
//        result = 31 * result + (contractorName?.hashCode() ?: 0)
//        result = 31 * result + (contractorAddress?.hashCode() ?: 0)
//        result = 31 * result + (recipient?.hashCode() ?: 0)
//        result = 31 * result + (supplier?.hashCode() ?: 0)
//        result = 31 * result + (nip?.hashCode() ?: 0)
//        return result
//    }
//
//    companion object CREATOR : Parcelable.Creator<Contractor> {
//        override fun createFromParcel(parcel: Parcel): Contractor {
//            return Contractor(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Contractor?> {
//            return arrayOfNulls(size)
//        }
//    }


