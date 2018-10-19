package com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryPriceOld {

    @SerializedName("price")
    @Expose
    var price: Long? = null
    @SerializedName("price_raw")
    @Expose
    var priceRaw: Long? = null

}
