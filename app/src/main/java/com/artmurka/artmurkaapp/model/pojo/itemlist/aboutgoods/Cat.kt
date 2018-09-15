package com.artmurka.artmurkaapp.model.pojo.itemlist.aboutgoods

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Cat {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null

}
