package com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.CityResponse


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Address {

    @SerializedName("Warehouses")
    @Expose
    var warehouses: Int? = null
    @SerializedName("MainDescription")
    @Expose
    var mainDescription: String? = null
    @SerializedName("Area")
    @Expose
    var area: String? = null
    @SerializedName("Region")
    @Expose
    var region: String? = null
    @SerializedName("SettlementTypeCode")
    @Expose
    var settlementTypeCode: String? = null
    @SerializedName("Ref")
    @Expose
    var ref: String? = null
    @SerializedName("DeliveryCity")
    @Expose
    var deliveryCity: String? = null
    @SerializedName("StreetsAvailability")
    @Expose
    var streetsAvailability: Boolean? = null

}