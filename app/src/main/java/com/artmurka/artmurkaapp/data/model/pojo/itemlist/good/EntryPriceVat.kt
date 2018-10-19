package com.artmurka.artmurkaapp.data.model.pojo.itemlist.good

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryPriceVat {

    @SerializedName("price_raw")
    @Expose
    var priceRaw: Int? = null
    @SerializedName("price")
    @Expose
    var price: String? = null

}
