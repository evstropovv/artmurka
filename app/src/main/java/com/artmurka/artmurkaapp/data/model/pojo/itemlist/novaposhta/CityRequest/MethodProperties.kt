package com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.CityRequest


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MethodProperties(@field:SerializedName("CityName")
                       @field:Expose
                       var cityRef: String?) {

    @SerializedName("Page")
    @Expose
    var page: String? = null

    @SerializedName("Warehouse")
    @Expose
    var warehouse: String? = null

    init {
        this.page = "1"
        this.warehouse = "1"
    }
}