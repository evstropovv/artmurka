package com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Paginator {

    @SerializedName("cur_page")
    @Expose
    var curPage: Long? = null
    @SerializedName("num_pages")
    @Expose
    var numPages: Long? = null

}
