package com.example.warehousemanagerapp.service

import com.example.warehousemanagerapp.util.JsonConst
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName(JsonConst.NAME)
    @Expose
    var name: String? = null,

    @SerializedName(JsonConst.PASSWORD)
    @Expose
    var password: String? = null
)