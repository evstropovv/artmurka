
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.WishList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;

public class Success {

    @SerializedName("sort")
    @Expose
    private Sort sort;

    @SerializedName("goods_list")
    @Expose
    private HashMap<String, GoodsListDescription> goodsList;


    @SerializedName("goods_count")
    @Expose
    private Long goodsCount;
    @SerializedName("duplicate")
    @Expose
    private Long duplicate;
    @SerializedName("meta_data")
    @Expose
    private MetaData metaData;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("filters")
    @Expose
    private String filters;
    @SerializedName("paginator")
    @Expose
    private Paginator paginator;

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public HashMap<String, GoodsListDescription>  getGoodsList() {
        return goodsList;
    }

    public void setGoodsList (HashMap <String, GoodsListDescription> goodsList) {
        this.goodsList = goodsList;
    }

    public Long getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Long goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Long getDuplicate() {
        return duplicate;
    }

    public void setDuplicate(Long duplicate) {
        this.duplicate = duplicate;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }

}
