package com.artmurka.artmurkaapp.data.model.pojo.itemlist.orders

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Currency {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("rate")
    @Expose
    var rate: String? = null

}
