package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkoutresponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Success {

    @SerializedName("msg")
    @Expose
    var msg: String? = null

    fun withMsg(msg: String): Success {
        this.msg = msg
        return this
    }

}