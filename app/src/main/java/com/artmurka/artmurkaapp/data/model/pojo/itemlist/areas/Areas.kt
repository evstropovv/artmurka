package com.artmurka.artmurkaapp.data.model.pojo.itemlist.areas

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Areas {

    @SerializedName("success")
    @Expose
    var isSuccess: Boolean = false
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
    var info: Info? = null
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
