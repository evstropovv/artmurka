package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryPriceOld {

    @SerializedName("price")
    @Expose
    var price: Int = 0
    @SerializedName("price_raw")
    @Expose
    var priceRaw: Int = 0

}
