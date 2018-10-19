package com.artmurka.artmurkaapp.data.model.pojo.itemlist.aboutgoods

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryPriceOld {

    @SerializedName("price_raw")
    @Expose
    var priceRaw: Int = 0
    @SerializedName("price")
    @Expose
    var price: Int = 0

}
