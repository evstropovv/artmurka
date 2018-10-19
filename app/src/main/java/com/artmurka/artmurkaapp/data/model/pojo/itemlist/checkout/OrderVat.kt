package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderVat {

    @SerializedName("vat_raw")
    @Expose
    var vatRaw: Long? = null
    @SerializedName("vat_type")
    @Expose
    var vatType: Long? = null
    @SerializedName("vat")
    @Expose
    var vat: Long? = null

}
