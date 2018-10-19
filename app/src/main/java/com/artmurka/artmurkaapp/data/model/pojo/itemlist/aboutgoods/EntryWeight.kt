package com.artmurka.artmurkaapp.data.model.pojo.itemlist.aboutgoods

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryWeight {

    @SerializedName("weight_raw")
    @Expose
    var weightRaw: Int = 0
    @SerializedName("weight")
    @Expose
    var weight: String? = null

}
