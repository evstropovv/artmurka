package com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.WarehousesRequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WarehouseRequest {

    @SerializedName("modelName")
    @Expose
    var modelName: String? = null
    @SerializedName("calledMethod")
    @Expose
    var calledMethod: String? = null
    @SerializedName("methodProperties")
    @Expose
    var methodProperties: MethodProperties? = null
    @SerializedName("apiKey")
    @Expose
    var apiKey: String? = null

    fun withModelName(modelName: String): WarehouseRequest {
        this.modelName = modelName
        return this
    }

    fun withCalledMethod(calledMethod: String): WarehouseRequest {
        this.calledMethod = calledMethod
        return this
    }

    fun withMethodProperties(methodProperties: MethodProperties): WarehouseRequest {
        this.methodProperties = methodProperties
        return this
    }

    fun withApiKey(apiKey: String): WarehouseRequest {
        this.apiKey = apiKey
        return this
    }

}
