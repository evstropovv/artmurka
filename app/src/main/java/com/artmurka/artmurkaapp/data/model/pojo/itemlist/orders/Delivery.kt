package com.artmurka.artmurkaapp.data.model.pojo.itemlist.orders

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Delivery {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("tax")
    @Expose
    var tax: String? = null

}
