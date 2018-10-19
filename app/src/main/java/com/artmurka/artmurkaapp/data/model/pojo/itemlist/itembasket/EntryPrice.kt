package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryPrice {

    @SerializedName("price")
    @Expose
    var price: String? = null
    @SerializedName("price_raw")
    @Expose
    var priceRaw: String? = null

}
