package com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.HashMap

class Success {

    @SerializedName("sort")
    @Expose
    var sort: Sort? = null

    @SerializedName("goods_list")
    @Expose
    var goodsList: HashMap<String, GoodsListDescription>? = null

    @SerializedName("goods_count")
    @Expose
    var goodsCount: Long? = null
    @SerializedName("duplicate")
    @Expose
    var duplicate: Long? = null
    @SerializedName("meta_data")
    @Expose
    var metaData: MetaData? = null
    @SerializedName("msg")
    @Expose
    var msg: String? = null

    @SerializedName("paginator")
    @Expose
    var paginator: Paginator? = null

}
