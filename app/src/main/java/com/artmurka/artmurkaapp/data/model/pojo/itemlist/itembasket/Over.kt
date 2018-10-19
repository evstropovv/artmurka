package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Over {

    @SerializedName("summ")
    @Expose
    var summ: Int = 0
    @SerializedName("summ_raw")
    @Expose
    var summRaw: String? = null

}
