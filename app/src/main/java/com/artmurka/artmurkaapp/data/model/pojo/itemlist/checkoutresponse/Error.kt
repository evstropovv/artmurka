package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkoutresponse


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Error {

    @SerializedName("code")
    @Expose
    var code: String? = null
    @SerializedName("msg")
    @Expose
    var msg: String? = null

    fun withCode(code: String): Error {
        this.code = code
        return this
    }

    fun withMsg(msg: String): Error {
        this.msg = msg
        return this
    }

}