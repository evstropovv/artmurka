package com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryWeight {

    @SerializedName("weight_raw")
    @Expose
    var weightRaw: String? = null
    @SerializedName("weight")
    @Expose
    var weight: Long? = null

}
