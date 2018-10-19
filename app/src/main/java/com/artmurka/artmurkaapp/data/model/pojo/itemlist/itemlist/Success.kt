package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.TreeMap

class Success {

    @SerializedName("sort")
    @Expose
    var sort: Sort? = null
    @SerializedName("cat_descr")
    @Expose
    var catDescr: String? = null
    @SerializedName("cat_id")
    @Expose
    var catId: String? = null

    @SerializedName("goods_list")
    @Expose
    val goodsList: TreeMap<String, GoodsProperties>? = null
    @SerializedName("duplicate")
    @Expose
    var duplicate: Int = 0
    @SerializedName("cat_img")
    @Expose
    var catImg: String? = null
    @SerializedName("meta_data")
    @Expose
    var metaData: MetaData? = null
    @SerializedName("cat_url")
    @Expose
    var catUrl: String? = null

    @SerializedName("goods_count")
    @Expose
    var goodsCount: Int = 0
    @SerializedName("cat_name")
    @Expose
    var catName: String? = null
    @SerializedName("paginator")
    @Expose
    var paginator: Paginator? = null

}
