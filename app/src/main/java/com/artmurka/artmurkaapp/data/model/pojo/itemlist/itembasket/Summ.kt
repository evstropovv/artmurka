package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Summ {

    @SerializedName("summ")
    @Expose
    var summ: String? = null
    @SerializedName("summ_raw")
    @Expose
    var summRaw: String? = null

}
