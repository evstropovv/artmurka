package com.artmurka.artmurkaapp.data.model.pojo.itemlist.aboutgoods

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.HashMap

class EntryPhoto {

    @SerializedName("others_photo")
    @Expose
    val othersPhoto: HashMap<String, SizePhoto>? = null

    @SerializedName("def_photo")
    @Expose
    var defPhoto: DefPhoto? = null

    @SerializedName("num_photos")
    @Expose
    var numPhotos: Int = 0

}
