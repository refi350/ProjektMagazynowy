package com.example.warehousemanagerapp.data

import android.os.Parcelable
import com.example.warehousemanagerapp.util.JsonConst
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
class ActionCommodities(
    @SerializedName(JsonConst.COMMODITY_ID)
    @Expose
    var commodityId: Long? = null,

    @SerializedName(JsonConst.QUANTITY)
    @Expose
    var quantity: Int? = null
): Parcelable