package com.example.warehousemanagerapp.data

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.example.warehousemanagerapp.util.JsonConst
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Warehouse (
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

    @SerializedName(JsonConst.IMAGE)
    @Expose
    var image: ByteArray? = null,

    @SerializedName(JsonConst.OWNER)
    @Expose
    var owner: Owner? = null,

    @SerializedName(JsonConst.CONTRACTORS)
    @Expose
    var contractors: List<Contractor>?,

    @SerializedName(JsonConst.COMMODITIES)
    @Expose
    var commodities: Parcelable?,

    @SerializedName(JsonConst.ORDERS)
    @Expose
    var orders: Parcelable?,

    @SerializedName(JsonConst.STORE_ACTIONS)
    @Expose
    var storeActions: Parcelable?
): Parcelable, Serializable {
    //@RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @Suppress("LeakingThis")
    private constructor(parcel: Parcel) : this (
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Address::class.java.classLoader),
        parcel.readString(),
        parcel.createByteArray(),
        parcel.readParcelable(Owner::class.java.classLoader),
        parcel.readParcelable(Contractor::class.java.classLoader),
        parcel.readParcelable(Commodity::class.java.classLoader),
        parcel.readParcelable(Order::class.java.classLoader),
        parcel.readParcelable(StoreAction::class.java.classLoader)
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Warehouse

        if (warehouseId != other.warehouseId) return false
        if (name != other.name) return false
        if (password != other.password) return false
        if (address != other.address) return false
        if (color != other.color) return false
        if (image != null) {
            if (other.image == null) return false
            if (!image.contentEquals(other.image)) return false
        } else if (other.image != null) return false
        if (owner != other.owner) return false
        if (contractors != other.contractors) return false
        if (commodities != other.commodities) return false
        if (orders != other.orders) return false
        if (storeActions != other.storeActions) return false

        return true
    }

    override fun hashCode(): Int {
        var result = warehouseId ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (password?.hashCode() ?: 0)
        result = 31 * result + (address?.hashCode() ?: 0)
        result = 31 * result + (color?.hashCode() ?: 0)
        result = 31 * result + (image?.contentHashCode() ?: 0)
        result = 31 * result + (owner?.hashCode() ?: 0)
        result = 31 * result + contractors.hashCode()
        result = 31 * result + commodities.hashCode()
        result = 31 * result + orders.hashCode()
        result = 31 * result + storeActions.hashCode()
        return result
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(warehouseId)
        parcel.writeString(name)
        parcel.writeString(password)
        parcel.writeString(color)
        parcel.writeByteArray(image)
        parcel.writeParcelable(address, flags)
        parcel.writeParcelable(owner, flags)
        parcel.writeList(contractors)
    }

    override fun describeContents(): Int = 0

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Warehouse> = object : Parcelable.Creator<Warehouse> {
            @RequiresApi(Build.VERSION_CODES.TIRAMISU)
            override fun createFromParcel(parcel: Parcel): Warehouse = Warehouse(parcel)

            override fun newArray(size: Int): Array<Warehouse?> = arrayOfNulls(size)
        }
    }
}