package com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.CityResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum {

    @SerializedName("TotalCount")
    @Expose
    var totalCount: Int? = null
    @SerializedName("Addresses")
    @Expose
    var addresses: List<Address>? = null

}