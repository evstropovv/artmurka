package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkoutresponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CheckoutResponse {

    @SerializedName("success")
    @Expose
    var success: Success? = null

    @SerializedName("error")
    @Expose
    var error: Error? = null

    fun withSuccess(success: Success): CheckoutResponse {
        this.success = success
        return this
    }

    fun withError(error: Error): CheckoutResponse {
        this.error = error
        return this
    }
}