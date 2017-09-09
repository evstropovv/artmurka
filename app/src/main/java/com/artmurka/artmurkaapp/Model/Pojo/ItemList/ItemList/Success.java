
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.TreeMap;

public class Success {

    @SerializedName("sort")
    @Expose
    private Sort sort;
    @SerializedName("cat_descr")
    @Expose
    private String catDescr;
    @SerializedName("cat_id")
    @Expose
    private String catId;


    @SerializedName("goods_list")
    @Expose
    private TreeMap<String, GoodsProperties> goodsList;


    @SerializedName("duplicate")
    @Expose
    private int duplicate;
    @SerializedName("cat_img")
    @Expose
    private String catImg;
    @SerializedName("meta_data")
    @Expose
    private MetaData metaData;
    @SerializedName("cat_url")
    @Expose
    private String catUrl;
    @SerializedName("parent_cat")
    @Expose
    private String parentCat;
    @SerializedName("goods_count")
    @Expose
    private int goodsCount;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("paginator")
    @Expose
    private Paginator paginator;
    @SerializedName("filters")
    @Expose
    private String filters;

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public String getCatDescr() {
        return catDescr;
    }

    public void setCatDescr(String catDescr) {
        this.catDescr = catDescr;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public TreeMap<String, GoodsProperties> getGoodsList() {
        return goodsList;
    }

    public int getDuplicate() {
        return duplicate;
    }

    public void setDuplicate(int duplicate) {
        this.duplicate = duplicate;
    }

    public String getCatImg() {
        return catImg;
    }

    public void setCatImg(String catImg) {
        this.catImg = catImg;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public String getCatUrl() {
        return catUrl;
    }

    public void setCatUrl(String catUrl) {
        this.catUrl = catUrl;
    }

    public String getParentCat() {
        return parentCat;
    }

    public void setParentCat(String parentCat) {
        this.parentCat = parentCat;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

}
