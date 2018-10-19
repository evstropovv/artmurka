package com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MetaData {

    @SerializedName("META_TITLE")
    @Expose
    var metatitle: String? = null
    @SerializedName("META_DESCRIPTION")
    @Expose
    var metadescription: String? = null

}
