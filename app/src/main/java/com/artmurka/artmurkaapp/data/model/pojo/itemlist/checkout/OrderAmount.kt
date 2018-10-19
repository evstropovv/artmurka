package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderAmount {

    @SerializedName("amount")
    @Expose
    var amount: String? = null
    @SerializedName("amount_raw")
    @Expose
    var amountRaw: Float? = null

}
