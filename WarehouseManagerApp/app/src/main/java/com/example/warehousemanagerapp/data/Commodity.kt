package com.example.warehousemanagerapp.data

import android.os.Parcel
import android.os.Parcelable
import com.example.warehousemanagerapp.util.JsonConst
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Commodity(
    @SerializedName(JsonConst.COMMODITIES_ID)
    @Expose
    var commodities: Int? = null,

    @SerializedName(JsonConst.COMMODITIES_NAME)
    @Expose
    var commoditiesName: String? = null,

    @SerializedName(JsonConst.COUNTER)
    @Expose
    var counter: Int? = null,

    @SerializedName(JsonConst.TEMP_COUNTER)
    @Expose
    var tempCounter: Int? = null,

    @SerializedName(JsonConst.CODE)
    @Expose
    var code: Int? = null,

    @SerializedName(JsonConst.DESCRIPTION)
    @Expose
    var description: String? = null,

    @SerializedName(JsonConst.UNIT)
    @Expose
    var unit: String? = null
): Parcelable
//    constructor(parcel: Parcel) : this(
//        parcel.readValue(Int::class.java.classLoader) as? Int,
//        parcel.readString(),
//        parcel.readValue(Int::class.java.classLoader) as? Int,
//        parcel.readValue(Int::class.java.classLoader) as? Int,
//        parcel.readValue(Int::class.java.classLoader) as? Int,
//        parcel.readString(),
//        parcel.readString()
//        //parcel.createByteArray()
//    )
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as Commodity
//
//        if (commodities != other.commodities) return false
//        if (commoditiesName != other.commoditiesName) return false
//        if (counter != other.counter) return false
//        if (tempCounter != other.tempCounter) return false
//        if (code != other.code) return false
//        if (description != other.description) return false
//        if (unit != other.unit) return false
////        if (image != null) {
////            if (other.image == null) return false
////            if (!image.contentEquals(other.image)) return false
////        } else if (other.image != null) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        var result = commodities ?: 0
//        result = 31 * result + (commoditiesName?.hashCode() ?: 0)
//        result = 31 * result + (counter ?: 0)
//        result = 31 * result + (tempCounter ?: 0)
//        result = 31 * result + (code ?: 0)
//        result = 31 * result + (description?.hashCode() ?: 0)
//        result = 31 * result + (unit?.hashCode() ?: 0)
//        //result = 31 * result + (image?.contentHashCode() ?: 0)
//        return result
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeValue(commodities)
//        parcel.writeString(commoditiesName)
//        parcel.writeValue(counter)
//        parcel.writeValue(tempCounter)
//        parcel.writeValue(code)
//        parcel.writeString(description)
//        parcel.writeString(unit)
//        //parcel.writeByteArray(image)
//    }
//
//    override fun describeContents(): Int = 0
//
//    companion object CREATOR : Parcelable.Creator<Commodity> {
//        override fun createFromParcel(parcel: Parcel): Commodity {
//            return Commodity(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Commodity?> {
//            return arrayOfNulls(size)
//        }
//    }

