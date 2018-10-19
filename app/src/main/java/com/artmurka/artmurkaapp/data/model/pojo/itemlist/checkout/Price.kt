package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Price {

    @SerializedName("price")
    @Expose
    val price: String? = null

    @SerializedName("price_raw")
    @Expose
    val priceRaw: Float? = null

}
