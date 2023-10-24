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
    var contractor: String? = null,

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
        parcel.readString(),
        parcel.createTypedArrayList(Commodity)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(orderId)
        parcel.writeString(submitTime)
        parcel.writeString(acceptTime)
        parcel.writeString(completedTime)
        parcel.writeString(orderStatus)
        parcel.writeString(contractor)
        parcel.writeTypedList(commoditiesList)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Order> {
        override fun createFromParcel(parcel: Parcel): Order {
            return Order(parcel)
        }

        override fun newArray(size: Int): Array<Order?> {
            return arrayOfNulls(size)
        }
    }

}
