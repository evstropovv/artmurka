package com.artmurka.artmurkaapp.data.model.pojo.itemlist.aboutgoods

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DefPhoto {

    @SerializedName("small")
    @Expose
    var small: String? = null
    @SerializedName("photo")
    @Expose
    var photo: String? = null
    @SerializedName("thumb")
    @Expose
    var thumb: String? = null

}
