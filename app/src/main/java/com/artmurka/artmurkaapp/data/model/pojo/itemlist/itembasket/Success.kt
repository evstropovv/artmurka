package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Success {

    @SerializedName("msg")
    @Expose
    var msg: String? = null
    @SerializedName("basket")
    @Expose
    var basket: Basket? = null

}
