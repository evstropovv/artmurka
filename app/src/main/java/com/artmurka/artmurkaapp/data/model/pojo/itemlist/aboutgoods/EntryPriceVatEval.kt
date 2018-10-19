package com.artmurka.artmurkaapp.data.model.pojo.itemlist.aboutgoods

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryPriceVatEval {

    @SerializedName("price_raw")
    @Expose
    var priceRaw: String? = null
    @SerializedName("price")
    @Expose
    var price: String? = null

}
