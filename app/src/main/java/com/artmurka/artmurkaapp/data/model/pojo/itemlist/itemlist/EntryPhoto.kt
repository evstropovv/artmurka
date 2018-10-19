package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryPhoto {

    @SerializedName("def_photo")
    @Expose
    var defPhoto: DefPhoto? = null
    @SerializedName("num_photos")
    @Expose
    var numPhotos: Int = 0


}
