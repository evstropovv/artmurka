package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderDiscount {

    @SerializedName("discount")
    @Expose
    var discount: Long? = null
    @SerializedName("discount_raw")
    @Expose
    var discountRaw: Long? = null

}
