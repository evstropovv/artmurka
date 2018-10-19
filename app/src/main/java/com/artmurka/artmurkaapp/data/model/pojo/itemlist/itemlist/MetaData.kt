package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MetaData {

    @SerializedName("META_DESCRIPTION")
    @Expose
    var metadescription: String? = null
    @SerializedName("META_TITLE")
    @Expose
    var metatitle: String? = null

}
