package com.artmurka.artmurkaapp.data.model.pojo.itemlist.good

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryStock {

    @SerializedName("stock")
    @Expose
    var stock: String? = null
    @SerializedName("stock_total")
    @Expose
    var stockTotal: String? = null

}
