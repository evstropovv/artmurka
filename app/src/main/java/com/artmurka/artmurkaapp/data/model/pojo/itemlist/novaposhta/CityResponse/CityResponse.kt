package com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.CityResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CityResponse {

    @SerializedName("success")
    @Expose
    var success: Boolean? = null
    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null
    @SerializedName("errors")
    @Expose
    var errors: List<Any>? = null
    @SerializedName("warnings")
    @Expose
    var warnings: List<Any>? = null
    @SerializedName("info")
    @Expose
    var info: List<Any>? = null
    @SerializedName("messageCodes")
    @Expose
    var messageCodes: List<Any>? = null
    @SerializedName("errorCodes")
    @Expose
    var errorCodes: List<Any>? = null
    @SerializedName("warningCodes")
    @Expose
    var warningCodes: List<Any>? = null
    @SerializedName("infoCodes")
    @Expose
    var infoCodes: List<Any>? = null

}