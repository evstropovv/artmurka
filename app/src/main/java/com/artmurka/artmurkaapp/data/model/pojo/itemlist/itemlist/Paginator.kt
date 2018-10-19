package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Paginator {

    @SerializedName("cur_page")
    @Expose
    var curPage: String? = null
    @SerializedName("num_pages")
    @Expose
    var numPages: Int = 0

}
