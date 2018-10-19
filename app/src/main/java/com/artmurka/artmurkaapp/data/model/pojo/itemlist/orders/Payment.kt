package com.artmurka.artmurkaapp.data.model.pojo.itemlist.orders

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Payment {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("topay")
    @Expose
    var topay: String? = null

}
