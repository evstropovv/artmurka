package com.artmurka.artmurkaapp.data.model.pojo.itemlist.good

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RecommendedProducts {

    @SerializedName("recomm_products")
    @Expose
    var recommProducts: RecommProducts? = null
    @SerializedName("present")
    @Expose
    var present: Int? = null

}
