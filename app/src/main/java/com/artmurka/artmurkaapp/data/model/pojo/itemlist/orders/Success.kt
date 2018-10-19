package com.artmurka.artmurkaapp.data.model.pojo.itemlist.orders

import java.util.HashMap
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Success {

    @SerializedName("page")
    @Expose
    var page: Long? = null
    @SerializedName("total")
    @Expose
    var total: Long? = null
    @SerializedName("pages_cnt")
    @Expose
    var pagesCnt: Long? = null
    @SerializedName("order_hide")
    @Expose
    var orderHide: HashMap<String, String>? = null
    @SerializedName("count")
    @Expose
    var count: String? = null
    @SerializedName("order_uids")
    @Expose
    var orderUids: List<String>? = null
    @SerializedName("per_page")
    @Expose
    var perPage: String? = null
    @SerializedName("profit")
    @Expose
    var profit: String? = null
    @SerializedName("orders")
    @Expose
    var orders: List<Order>? = null
    @SerializedName("order_fields")
    @Expose
    var orderFields: OrderFields? = null
    @SerializedName("order_status")
    @Expose
    var orderStatus: HashMap<String, String>? = null

}
