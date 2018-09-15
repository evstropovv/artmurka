package com.artmurka.artmurkaapp.model.pojo.itemlist.categories


import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken

import java.lang.reflect.Type
import java.util.Collections

class SuccessDeserelised : JsonDeserializer<Success> {
    //class need for parsing dinamicly "List<Child>" that can be null or "" or Child...

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Success {

        val jObj = json.asJsonObject
        val jElement = jObj.get("childs")
        val catImg = jObj.get("cat_img").asString
        val catDescr = jObj.get("cat_descr").asString
        val goodsCount = jObj.get("goods_count").asInt
        val catId = jObj.get("cat_id").asString
        val catName = jObj.get("cat_name").asString
        val catUrl = jObj.get("cat_url").asString

        var childs = emptyList<Success>()
        if (jElement.isJsonArray) {
            childs = context.deserialize(jElement.asJsonArray, object : TypeToken<List<Success>>() {

            }.type)
        }
        val success = Success()
        success.catImg = catImg
        success.childs = childs
        success.catDescr = catDescr
        success.goodsCount = goodsCount
        success.catId = catId
        success.catName = catName
        success.catUrl = catUrl

        return success
    }
}
