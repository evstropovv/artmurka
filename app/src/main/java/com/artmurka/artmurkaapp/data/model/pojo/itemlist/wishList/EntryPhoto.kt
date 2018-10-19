package com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryPhoto {


    @SerializedName("num_photos")
    @Expose
    var numPhotos: Long? = null
    @SerializedName("def_photo")
    @Expose
    var defPhoto: DefPhoto? = null

}
