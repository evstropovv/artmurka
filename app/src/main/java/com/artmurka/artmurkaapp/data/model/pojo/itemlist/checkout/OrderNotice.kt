package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderNotice {

    @SerializedName("notice_err")
    @Expose
    var noticeErr: String? = null
    @SerializedName("notice")
    @Expose
    var notice: String? = null

}
