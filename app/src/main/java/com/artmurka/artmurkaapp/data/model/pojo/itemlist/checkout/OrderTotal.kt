package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderTotal {

    @SerializedName("total")
    @Expose
    var total: String? = null
    @SerializedName("total_raw")
    @Expose
    var totalRaw: Float? = null

}
