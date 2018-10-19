package com.artmurka.artmurkaapp.data.model.pojo.itemlist.good

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryPhoto {

    @SerializedName("others_photo")
    @Expose
    var othersPhoto: String? = null
    @SerializedName("def_photo")
    @Expose
    var defPhoto: DefPhoto? = null
    @SerializedName("num_photos")
    @Expose
    var numPhotos: Int? = null

}
