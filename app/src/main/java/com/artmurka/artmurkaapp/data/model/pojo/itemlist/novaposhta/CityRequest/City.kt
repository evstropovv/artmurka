package com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.CityRequest


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class City {

    @SerializedName("apiKey")
    @Expose
    var apiKey: String? = null
    @SerializedName("modelName")
    @Expose
    var modelName: String? = null
    @SerializedName("calledMethod")
    @Expose
    var calledMethod: String? = null
    @SerializedName("methodProperties")
    @Expose
    var methodProperties: MethodProperties? = null

}