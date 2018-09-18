package com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.WarehousesResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WarehouseResponse {

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

    fun withSuccess(success: Boolean): WarehouseResponse {
        this.isSuccess = success
        return this
    }

    fun withData(data: List<Datum>): WarehouseResponse {
        this.data = data
        return this
    }

    fun withErrors(errors: List<Any>): WarehouseResponse {
        this.errors = errors
        return this
    }

    fun withWarnings(warnings: List<Any>): WarehouseResponse {
        this.warnings = warnings
        return this
    }

    fun withInfo(info: Info): WarehouseResponse {
        this.info = info
        return this
    }

    fun withMessageCodes(messageCodes: List<Any>): WarehouseResponse {
        this.messageCodes = messageCodes
        return this
    }

    fun withErrorCodes(errorCodes: List<Any>): WarehouseResponse {
        this.errorCodes = errorCodes
        return this
    }

    fun withWarningCodes(warningCodes: List<Any>): WarehouseResponse {
        this.warningCodes = warningCodes
        return this
    }

    fun withInfoCodes(infoCodes: List<Any>): WarehouseResponse {
        this.infoCodes = infoCodes
        return this
    }

}
