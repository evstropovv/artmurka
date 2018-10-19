package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderWeight {

    @SerializedName("weight_raw")
    @Expose
    var weightRaw: Long? = null
    @SerializedName("weight")
    @Expose
    var weight: String? = null

}
