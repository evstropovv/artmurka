package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Categories;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Child {

    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("cat_url")
    @Expose
    private String catUrl;
    @SerializedName("goods_count")
    @Expose
    private int goodsCount;
    @SerializedName("cat_id")
    @Expose
    private String catId;

    @SerializedName("childs")
    @Expose
    private String childs;
    @SerializedName("cat_descr")
    @Expose
    private String catDescr;
    @SerializedName("cat_img")
    @Expose
    private String catImg;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatUrl() {
        return catUrl;
    }

    public void setCatUrl(String catUrl) {
        this.catUrl = catUrl;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getChilds() {
        return childs;
    }

    public void setChilds(String childs) {
        this.childs = childs;
    }

    public String getCatDescr() {
        return catDescr;
    }

    public void setCatDescr(String catDescr) {
        this.catDescr = catDescr;
    }

    public String getCatImg() {
        return catImg;
    }

    public void setCatImg(String catImg) {
        this.catImg = catImg;
    }

}