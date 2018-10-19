package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Cnt {

    @SerializedName("pos")
    @Expose
    var pos: Long? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}
