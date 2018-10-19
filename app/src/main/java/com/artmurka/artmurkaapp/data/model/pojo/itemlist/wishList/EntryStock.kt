package com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryStock {

    @SerializedName("stock_total")
    @Expose
    var stockTotal: String? = null
    @SerializedName("stock")
    @Expose
    var stock: String? = null

}
