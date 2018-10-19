package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryWeight {

    @SerializedName("weight")
    @Expose
    var weight: Int = 0
    @SerializedName("weight_raw")
    @Expose
    var weightRaw: Int = 0

}
