package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryPhoto {

    @SerializedName("photo")
    @Expose
    var photo: String? = null
    @SerializedName("small")
    @Expose
    var small: String? = null
    @SerializedName("thumb")
    @Expose
    var thumb: String? = null

}
