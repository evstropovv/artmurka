package com.artmurka.artmurkaapp.data.model.pojo.itemlist.aboutgoods

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryRating {

    @SerializedName("rated")
    @Expose
    var rated: String? = null
    @SerializedName("rating_num")
    @Expose
    var ratingNum: String? = null
    @SerializedName("rating_num_float")
    @Expose
    var ratingNumFloat: Int = 0
    @SerializedName("rating")
    @Expose
    var rating: String? = null

}
