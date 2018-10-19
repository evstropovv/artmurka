package com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Slist {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("field")
    @Expose
    var field: String? = null

}
