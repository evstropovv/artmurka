package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sum {

    @SerializedName("sum_raw")
    @Expose
    var sumRaw: Float? = null
    @SerializedName("name")
    @Expose
    var sum: String? = null

}
