package com.example.warehousemanagerapp.data

import android.os.Parcel
import android.os.Parcelable
import com.example.warehousemanagerapp.util.JsonConst
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Order(
    @SerializedName(JsonConst.ORDER_ID)
    @Expose
    var orderId: Int? = null,

    @SerializedName(JsonConst.SUBMIT_TIME)
    @Expose
    var submitTime: String? = null,

    @SerializedName(JsonConst.ACCEPT_TIME)
    @Expose
    var acceptTime: String? = null,

    @SerializedName(JsonConst.COMPLETED_TIME)
    @Expose
    var completedTime: String? = null,

    @SerializedName(JsonConst.ORDER_STATUS)
    @Expose
    var orderStatus: String? = null,

    @SerializedName(JsonConst.CONTRACTOR)
    @Expose
    var contractor: Contractor? = null,

    @SerializedName(JsonConst.COMMODITIES_LIST)
    @Expose
    var commoditiesList: List<Commodity>? = null
): Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Contractor::class.java.classLoader) as? Contractor,
        parcel.createTypedArrayList(Commodity)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(orderId)
        parcel.writeString(submitTime)
        parcel.writeString(acceptTime)
        parcel.writeString(completedTime)
        parcel.writeString(orderStatus)
        parcel.writeValue(contractor)
        parcel.writeTypedList(commoditiesList)
    }

    override fun describeContents(): Int = 0
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Order

        if (orderId != other.orderId) return false
        if (submitTime != other.submitTime) return false
        if (acceptTime != other.acceptTime) return false
        if (completedTime != other.completedTime) return false
        if (orderStatus != other.orderStatus) return false
        if (contractor != other.contractor) return false
        if (commoditiesList != other.commoditiesList) return false

        return true
    }

    override fun hashCode(): Int {
        var result = orderId ?: 0
        result = 31 * result + (submitTime?.hashCode() ?: 0)
        result = 31 * result + (acceptTime?.hashCode() ?: 0)
        result = 31 * result + (completedTime?.hashCode() ?: 0)
        result = 31 * result + (orderStatus?.hashCode() ?: 0)
        result = 31 * result + (contractor?.hashCode() ?: 0)
        result = 31 * result + (commoditiesList?.hashCode() ?: 0)
        return result
    }

    companion object CREATOR : Parcelable.Creator<Order> {
        override fun createFromParcel(parcel: Parcel): Order {
            return Order(parcel)
        }

        override fun newArray(size: Int): Array<Order?> {
            return arrayOfNulls(size)
        }
    }

}
