package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sort {

    @SerializedName("order")
    @Expose
    var order: String? = null
    @SerializedName("slist")
    @Expose
    var slist: List<Slist>? = null
    @SerializedName("sort")
    @Expose
    var sort: String? = null

}
