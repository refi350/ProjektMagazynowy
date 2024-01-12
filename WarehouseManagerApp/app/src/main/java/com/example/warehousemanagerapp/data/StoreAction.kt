package com.example.warehousemanagerapp.data

import android.os.Parcel
import android.os.Parcelable
import com.example.warehousemanagerapp.service.StoreActionDeserializer
import com.example.warehousemanagerapp.util.JsonConst
import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
@JsonAdapter(StoreActionDeserializer::class)
open class StoreAction(
    @SerializedName(JsonConst.TYPE)
    @Expose
    var type: String? = null,

    @SerializedName(JsonConst.STORE_ACTION_ID)
    @Expose
    var storeActionId: Int? = null,

    @SerializedName(JsonConst.STORE_ACTION_DATE)
    @Expose
    var date: String? = null,

    @SerializedName(JsonConst.CONTRACTOR)
    @Expose
    var contractor: Contractor? = null,

    @SerializedName(JsonConst.COMMODITIES)
    @Expose
    var actionCommodities: MutableList<ActionCommodities> = mutableListOf(), //List?

    @SerializedName(JsonConst.DOC_NUMBER)
    @Expose
    var docNumber: Long? = null,

    ) : Parcelable {
    constructor(
        actionCommodities: ActionCommodities, date: String?, contractor: Contractor?, type: String?
    ): this() {
        this.actionCommodities.add(actionCommodities)
        this.date = date
        this.contractor = contractor
        this.type = type
    }

    override fun toString(): String {
        return "StoreAction(type=$type, storeActionId=$storeActionId, date=$date," +
                " contractor=$contractor, actionCommodities=$actionCommodities," +
                " docNumber=$docNumber)"
    }
}

//    constructor(parcel: Parcel) : this(
//        parcel.readValue(Int::class.java.classLoader) as? Int,
//        parcel.readString(),
//        parcel.readParcelable(Contractor::class.java.classLoader),
//        parcel.createTypedArrayList(Commodity),
//        parcel.readValue(Int::class.java.classLoader) as? Int
//    )

//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeValue(storeActionId)
//        parcel.writeString(date)
//        parcel.writeParcelable(contractor, flags)
//        parcel.writeTypedList(commodities)
//        parcel.writeValue(docNumber)
//    }
//
//    override fun describeContents(): Int = 0
//
//    companion object CREATOR : Parcelable.Creator<StoreAction> {
//        override fun createFromParcel(parcel: Parcel): StoreAction {
//            return StoreAction(parcel)
//        }
//
//        override fun newArray(size: Int): Array<StoreAction?> {
//            return arrayOfNulls(size)
//        }
//    }

