package com.artmurka.artmurkaapp.Model.Retrofit;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Success {

    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("cat_descr")
    @Expose
    private String catDescr;
    @SerializedName("goods_count")
    @Expose
    private int goodsCount;
    @SerializedName("cat_img")
    @Expose
    private String catImg;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("meta_data")
    @Expose
    private MetaData metaData;
    @SerializedName("childs")
    @Expose
    private String childs;
    @SerializedName("cat_url")
    @Expose
    private String catUrl;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatDescr() {
        return catDescr;
    }

    public void setCatDescr(String catDescr) {
        this.catDescr = catDescr;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getCatImg() {
        return catImg;
    }

    public void setCatImg(String catImg) {
        this.catImg = catImg;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public String getChilds() {
        return childs;
    }

    public void setChilds(String childs) {
        this.childs = childs;
    }

    public String getCatUrl() {
        return catUrl;
    }

    public void setCatUrl(String catUrl) {
        this.catUrl = catUrl;
    }

}