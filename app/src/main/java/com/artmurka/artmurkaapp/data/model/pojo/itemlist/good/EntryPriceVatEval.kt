package com.artmurka.artmurkaapp.data.model.pojo.itemlist.good

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryPriceVatEval {

    @SerializedName("price")
    @Expose
    var price: String? = null
    @SerializedName("price_raw")
    @Expose
    var priceRaw: String? = null

}
