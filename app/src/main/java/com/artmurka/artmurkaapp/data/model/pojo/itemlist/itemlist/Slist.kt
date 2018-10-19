package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Slist {

    @SerializedName("field")
    @Expose
    var field: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}
