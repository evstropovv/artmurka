package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderTopay {

    @SerializedName("topay")
    @Expose
    var topay: String? = null
    @SerializedName("topay_curr")
    @Expose
    var topayCurr: String? = null
    @SerializedName("topay_raw")
    @Expose
    var topayRaw: Float? = null

}
