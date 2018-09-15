package com.artmurka.artmurkaapp.model.pojo.itemlist.categories


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Success  : Parcelable {

    @SerializedName("cat_img")
    @Expose
    var catImg: String? = null
    @SerializedName("childs")
    @Expose
    var childs: List<Success>? = null
    @SerializedName("cat_descr")
    @Expose
    var catDescr: String? = null

    @SerializedName("goods_count")
    @Expose
    var goodsCount: Int = 0
    @SerializedName("cat_id")
    @Expose
    var catId: String? = null
    @SerializedName("cat_name")
    @Expose
    var catName: String? = null
    @SerializedName("cat_url")
    @Expose
    var catUrl: String? = null

}