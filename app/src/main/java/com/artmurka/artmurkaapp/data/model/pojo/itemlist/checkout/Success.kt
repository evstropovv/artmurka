package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.HashMap

class Success {
    @SerializedName("payment_list")
    @Expose
    var paymentList: HashMap<String, PaymentDescription>? = null

    @SerializedName("delivery_list")
    @Expose
    var deliveryList: HashMap<String, DeliveryDescription>? = null

    @SerializedName("order_data")
    @Expose
    var orderData: OrderData? = null
    @SerializedName("current_delivery_id")
    @Expose
    var currentDeliveryId: Long? = null
    @SerializedName("current_payment_id")
    @Expose
    var currentPaymentId: Long? = null
    @SerializedName("num_entries")
    @Expose
    var numEntries: Long? = null
    @SerializedName("order_content")
    @Expose
    var orderContent: OrderContent? = null

}
