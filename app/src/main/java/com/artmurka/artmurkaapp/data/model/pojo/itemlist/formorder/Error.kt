package com.artmurka.artmurkaapp.data.model.pojo.itemlist.formorder

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Error {

    @SerializedName("msg")
    @Expose
    var msg: String? = null
    @SerializedName("code")
    @Expose
    var code: String? = null

}