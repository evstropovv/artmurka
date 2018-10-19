package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryCat {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("pid")
    @Expose
    var pid: String? = null
    @SerializedName("purl")
    @Expose
    var purl: Any? = null
    @SerializedName("pname")
    @Expose
    var pname: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null

}
