package com.artmurka.artmurkaapp.data.model.pojo.itemlist.good

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DefPhoto {

    @SerializedName("photo")
    @Expose
    var photo: String? = null
    @SerializedName("thumb")
    @Expose
    var thumb: String? = null
    @SerializedName("small")
    @Expose
    var small: String? = null

}
