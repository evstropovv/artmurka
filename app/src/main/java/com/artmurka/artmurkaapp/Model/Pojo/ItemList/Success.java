
package com.artmurka.artmurkaapp.Model.Pojo.ItemList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Success {

    @SerializedName("goods_list")
    @Expose
    private GoodsList goodsList;
    @SerializedName("parent_cat")
    @Expose
    private String parentCat;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("cat_img")
    @Expose
    private String catImg;
    @SerializedName("meta_data")
    @Expose
    private MetaData metaData;
    @SerializedName("goods_count")
    @Expose
    private int goodsCount;
    @SerializedName("duplicate")
    @Expose
    private int duplicate;
    @SerializedName("cat_descr")
    @Expose
    private String catDescr;
    @SerializedName("filters")
    @Expose
    private String filters;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("paginator")
    @Expose
    private Paginator paginator;
    @SerializedName("sort")
    @Expose
    private Sort sort;
    @SerializedName("cat_url")
    @Expose
    private String catUrl;

    public GoodsList getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(GoodsList goodsList) {
        this.goodsList = goodsList;
    }

    public String getParentCat() {
        return parentCat;
    }

    public void setParentCat(String parentCat) {
        this.parentCat = parentCat;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
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

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public int getDuplicate() {
        return duplicate;
    }

    public void setDuplicate(int duplicate) {
        this.duplicate = duplicate;
    }

    public String getCatDescr() {
        return catDescr;
    }

    public void setCatDescr(String catDescr) {
        this.catDescr = catDescr;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
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

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public String getCatUrl() {
        return catUrl;
    }

    public void setCatUrl(String catUrl) {
        this.catUrl = catUrl;
    }

}
