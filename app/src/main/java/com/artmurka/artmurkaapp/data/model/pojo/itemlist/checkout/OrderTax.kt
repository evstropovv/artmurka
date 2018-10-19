package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderTax {

    @SerializedName("tax_raw")
    @Expose
    var taxRaw: Long? = null
    @SerializedName("tax")
    @Expose
    var tax: Long? = null

}
