package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Basket {

    @SerializedName("items")
    @Expose
    var items: List<Item>? = null
    @SerializedName("total")
    @Expose
    var total: String? = null
    @SerializedName("weight")
    @Expose
    var weight: String? = null
    @SerializedName("info")
    @Expose
    var info: String? = null
    @SerializedName("goods_count")
    @Expose
    var goodsCount: Int = 0
    @SerializedName("items_count")
    @Expose
    var itemsCount: Int = 0
    @SerializedName("discount")
    @Expose
    var discount: Int = 0

}
