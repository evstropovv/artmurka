package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderDesc {

    @SerializedName("cnt")
    @Expose
    var cnt: String? = null
    @SerializedName("goods_id")
    @Expose
    var goodsId: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("sum")
    @Expose
    var sum: Sum? = null
    @SerializedName("warning")
    @Expose
    var warning: String? = null
    @SerializedName("price")
    @Expose
    var price: Price? = null

    var orderPosition: String? = null

}
