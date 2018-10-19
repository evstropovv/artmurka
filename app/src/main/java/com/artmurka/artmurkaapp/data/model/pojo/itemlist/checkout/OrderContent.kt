package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.HashMap

class OrderContent {

    @SerializedName("order_goods_fields")
    @Expose
    var orderGoodsFields: OrderGoodsFields? = null
    @SerializedName("order_goods")
    @Expose
    var orderGoods: HashMap<String, OrderDesc>? = null

}
