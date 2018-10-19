package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntryRating {

    @SerializedName("rating_num_float")
    @Expose
    var ratingNumFloat: Int = 0
    @SerializedName("rating_num")
    @Expose
    var ratingNum: String? = null
    @SerializedName("rated")
    @Expose
    var rated: String? = null
    @SerializedName("rating")
    @Expose
    var rating: String? = null

}
