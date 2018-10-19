package com.artmurka.artmurkaapp.data.model.pojo.itemlist.aboutgoods

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SizePhoto(@field:SerializedName("photo")
                @field:Expose
                var photo: String?) {

    @SerializedName("thumb")
    @Expose
    var thumb: String? = null
    @SerializedName("small")
    @Expose
    var small: String? = null

}
