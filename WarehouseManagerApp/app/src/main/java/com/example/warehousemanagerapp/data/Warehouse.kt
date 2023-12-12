package com.example.warehousemanagerapp.data

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.example.warehousemanagerapp.util.JsonConst
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Warehouse(
    @SerializedName(JsonConst.WAREHOUSE_ID)
    @Expose
    var warehouseId: Int? = null,

    @SerializedName(JsonConst.NAME)
    @Expose
    var name: String? = null,

    @SerializedName(JsonConst.PASSWORD)
    @Expose
    var password: String? = null,

    @SerializedName(JsonConst.ADDRESS)
    @Expose
    var address: Address? = null,

    @SerializedName(JsonConst.COLOR)
    @Expose
    var color: String? = null, //???

    @SerializedName(JsonConst.OWNER)
    @Expose
    var owner: Owner? = null,

    @SerializedName(JsonConst.CONTRACTORS)
    @Expose
    var contractors: List<Contractor> = emptyList(),

    @SerializedName(JsonConst.COMMODITIES)
    @Expose
    var commodities: List<Commodity> = emptyList(),

    @SerializedName(JsonConst.ORDERS)
    @Expose
    var orders: List<Order> = emptyList(),

    @SerializedName(JsonConst.STORE_ACTIONS)
    @Expose
    var storeActions: List<StoreAction> = emptyList()
): Parcelable {
//    //@RequiresApi(Build.VERSION_CODES.TIRAMISU)
//    private constructor(parcel: Parcel) : this (
//        parcel.readValue(Int::class.java.classLoader) as? Int,
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readParcelable(Address::class.java.classLoader),
//        parcel.readString(),
//        parcel.readParcelable(Owner::class.java.classLoader),
//        parcel.createTypedArrayList(Contractor.CREATOR) ?: emptyList(),
//        parcel.createTypedArrayList(Commodity.CREATOR) ?: emptyList(),
//        parcel.createTypedArrayList(Order.CREATOR) ?: emptyList(),
//        //parcel.cre ?: emptyList()
//    )
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as Warehouse
//
//        if (warehouseId != other.warehouseId) return false
//        if (name != other.name) return false
//        if (password != other.password) return false
//        if (address != other.address) return false
//        if (color != other.color) return false
//        if (owner != other.owner) return false
//        if (contractors != other.contractors) return false
//        if (commodities != other.commodities) return false
//        if (orders != other.orders) return false
//        if (storeActions != other.storeActions) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        var result = warehouseId ?: 0
//        result = 31 * result + (name?.hashCode() ?: 0)
//        result = 31 * result + (password?.hashCode() ?: 0)
//        result = 31 * result + (address?.hashCode() ?: 0)
//        result = 31 * result + (color?.hashCode() ?: 0)
//        result = 31 * result + (owner?.hashCode() ?: 0)
//        result = 31 * result + contractors.hashCode()
//        result = 31 * result + commodities.hashCode()
//        result = 31 * result + orders.hashCode()
//        result = 31 * result + storeActions.hashCode()
//        return result
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeValue(warehouseId)
//        parcel.writeString(name)
//        parcel.writeString(password)
//        parcel.writeString(color)
//        parcel.writeParcelable(address, flags)
//        parcel.writeParcelable(owner, flags)
//        parcel.writeTypedList(contractors)
//        parcel.writeTypedList(commodities)
//        parcel.writeTypedList(orders)
//        parcel.writeTypedList(storeActions)
//    }
//
//    override fun describeContents(): Int = 0
//
//    companion object {
//        @JvmField
//        val CREATOR: Parcelable.Creator<Warehouse> = object : Parcelable.Creator<Warehouse> {
//            override fun createFromParcel(parcel: Parcel): Warehouse = Warehouse(parcel)
//
//            override fun newArray(size: Int): Array<Warehouse?> = arrayOfNulls(size)
//        }
//    }
}