package com.example.warehousemanagerapp.service

import com.example.warehousemanagerapp.data.Receipt
import com.example.warehousemanagerapp.data.Release
import com.example.warehousemanagerapp.data.StoreAction
import com.example.warehousemanagerapp.util.JsonConst
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class StoreActionDeserializer: JsonDeserializer<StoreAction> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): StoreAction {
        val jsonObject = json.asJsonObject

        return when (val type = jsonObject.getAsJsonPrimitive(JsonConst.TYPE).asString) {
            "Release" -> context!!.deserialize(json, Release::class.java)
            "Receipt" -> context!!.deserialize(json, Receipt::class.java)
            else -> throw IllegalArgumentException("Unknown type: $type")
        }
    }
}

//import com.google.gson.Gson
//import com.google.gson.JsonDeserializationContext
//import com.google.gson.JsonDeserializer
//import com.google.gson.JsonElement
//import com.google.gson.annotations.JsonAdapter
//import java.lang.reflect.Type
//import java.time.LocalDateTime
//import java.time.format.DateTimeFormatter
//
//data class Contractor(
//    val id: Int,
//    val name: String,
//    val address: String?,
//    val recipient: Boolean,
//    val supplier: Boolean,
//    val nip: String
//)
//
//data class Release(
//    val type: String,
//    val id: Int,
//    val date: LocalDateTime,
//    val contractor: Contractor,
//    val commodities: List<String>,
//    val docNumber: Int
//) : StoreAction(type, id, date)
//
//data class Receipt(
//    val type: String,
//    val id: Int,
//    val date: LocalDateTime,
//    val contractor: Contractor,
//    val commodities: List<String>,
//    val docNumber: Int
//) : StoreAction(type, id, date)
//
//@JsonAdapter(StoreActionDeserializer::class)
//open class StoreAction(
//    open val type: String,
//    open val id: Int,
//    open val date: LocalDateTime
//)
//
//class StoreActionDeserializer : JsonDeserializer<StoreAction> {
//    override fun deserialize(
//        json: JsonElement,
//        typeOfT: Type?,
//        context: JsonDeserializationContext?
//    ): StoreAction {
//        val jsonObject = json.asJsonObject
//        val type = jsonObject.getAsJsonPrimitive("type").asString
//
//        return when (type) {
//            "Release" -> context!!.deserialize(json, Release::class.java)
//            "Receipt" -> context!!.deserialize(json, Receipt::class.java)
//            else -> throw IllegalArgumentException("Unknown type: $type")
//        }
//    }
//}
//
//fun main() {
//    val gson = Gson()
//    val json = """
//        {
//            "type": "Receipt",
//            "id": 1753,
//            "date": "2023-12-05T06:41:32",
//            "contractor": {
//                "id": 302,
//                "name": "Nump",
//                "address": null,
//                "recipient": true,
//                "supplier": true,
//                "nip": "4534343234"
//            },
//            "commodities": [],
//            "doc_number": 56
//        }
//    """.trimIndent()
//
//    val storeAction: StoreAction = gson.fromJson(json, StoreAction::class.java)
//
//    when (storeAction) {
//        is Release -> println("It's a Release!")
//        is Receipt -> println("It's a Receipt!")
//    }
//}