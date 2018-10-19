package com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.Areas


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum {

    @SerializedName("Ref")
    @Expose
    var ref: String? = null
    @SerializedName("AreasCenter")
    @Expose
    var areasCenter: String? = null
    @SerializedName("Description")
    @Expose
    var description: String? = null

    override fun toString(): String {
        return description!!
    }
}