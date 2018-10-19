package com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.WarehousesResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Info {

    @SerializedName("totalCount")
    @Expose
    var totalCount: String? = null

    fun withTotalCount(totalCount: String): Info {
        this.totalCount = totalCount
        return this
    }

}
