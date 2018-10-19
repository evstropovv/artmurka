package com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryPrice {

    @SerializedName("price_raw")
    @Expose
    var priceRaw: Long? = null
    @SerializedName("price")
    @Expose
    var price: String? = null

}
