package com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sort {

    @SerializedName("sort")
    @Expose
    var sort: String? = null
    @SerializedName("order")
    @Expose
    var order: String? = null
    @SerializedName("slist")
    @Expose
    var slist: List<Slist>? = null

}
