package com.artmurka.artmurkaapp.data.model.pojo.itemlist.good

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryMetaData {

    @SerializedName("meta_title")
    @Expose
    var metaTitle: String? = null
    @SerializedName("meta_description")
    @Expose
    var metaDescription: String? = null

}
