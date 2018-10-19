package com.artmurka.artmurkaapp.data.model.pojo.itemlist.orders

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Order {

    @SerializedName("add_date")
    @Expose
    var addDate: String? = null
    @SerializedName("delivery")
    @Expose
    var delivery: Delivery? = null
    @SerializedName("nom")
    @Expose
    var nom: String? = null
    @SerializedName("payment")
    @Expose
    var payment: Payment? = null
    @SerializedName("rem")
    @Expose
    var rem: String? = null
    @SerializedName("currency")
    @Expose
    var currency: Currency? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("mcml")
    @Expose
    var mcml: String? = null
    @SerializedName("order_hash")
    @Expose
    var orderHash: String? = null
    @SerializedName("m1c")
    @Expose
    var m1c: String? = null
    @SerializedName("hide")
    @Expose
    var hide: String? = null
    @SerializedName("ip")
    @Expose
    var ip: String? = null
    @SerializedName("discount")
    @Expose
    var discount: Discount? = null
    @SerializedName("akey")
    @Expose
    var akey: String? = null
    @SerializedName("fields")
    @Expose
    var fields: Fields? = null
    @SerializedName("cnt")
    @Expose
    var cnt: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("user")
    @Expose
    var user: String? = null
    @SerializedName("mod_date")
    @Expose
    var modDate: String? = null
    @SerializedName("profit")
    @Expose
    var profit: String? = null
    @SerializedName("uid")
    @Expose
    var uid: String? = null
    @SerializedName("amount")
    @Expose
    var amount: String? = null
    @SerializedName("referer")
    @Expose
    var referer: String? = null
    @SerializedName("refhost")
    @Expose
    var refhost: String? = null

}
