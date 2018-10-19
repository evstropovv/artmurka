package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderGoodsFields {

    @SerializedName("sum")
    @Expose
    var sum: Sum? = null
    @SerializedName("price")
    @Expose
    var price: Price? = null
    @SerializedName("cnt")
    @Expose
    var cnt: Cnt? = null
    @SerializedName("name")
    @Expose
    var name: Name? = null

}
