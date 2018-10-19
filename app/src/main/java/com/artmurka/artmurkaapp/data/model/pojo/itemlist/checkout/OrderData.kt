package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderData {

    @SerializedName("order_info")
    @Expose
    var orderInfo: String? = null
    @SerializedName("order_tax")
    @Expose
    var orderTax: OrderTax? = null
    @SerializedName("order_vat")
    @Expose
    var orderVat: OrderVat? = null
    @SerializedName("order_total")
    @Expose
    var orderTotal: OrderTotal? = null
    @SerializedName("order_notice")
    @Expose
    var orderNotice: OrderNotice? = null
    @SerializedName("order_weight")
    @Expose
    var orderWeight: OrderWeight? = null
    @SerializedName("order_amount")
    @Expose
    var orderAmount: OrderAmount? = null
    @SerializedName("order_topay")
    @Expose
    var orderTopay: OrderTopay? = null
    @SerializedName("order_uid")
    @Expose
    var orderUid: String? = null
    @SerializedName("order_discount")
    @Expose
    var orderDiscount: OrderDiscount? = null

}
