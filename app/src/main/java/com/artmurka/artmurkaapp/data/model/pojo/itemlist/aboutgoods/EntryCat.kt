package com.artmurka.artmurkaapp.data.model.pojo.itemlist.aboutgoods

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryCat {

    @SerializedName("shop_url")
    @Expose
    var shopUrl: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}
