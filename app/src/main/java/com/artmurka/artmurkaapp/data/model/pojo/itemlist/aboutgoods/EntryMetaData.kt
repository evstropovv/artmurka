package com.artmurka.artmurkaapp.data.model.pojo.itemlist.aboutgoods

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryMetaData {

    @SerializedName("meta_description")
    @Expose
    var metaDescription: String? = null
    @SerializedName("meta_title")
    @Expose
    var metaTitle: String? = null

}
