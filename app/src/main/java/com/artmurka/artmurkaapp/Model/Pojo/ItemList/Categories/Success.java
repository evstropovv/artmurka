package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Categories;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Success {

    public Success(String catImg, List<Child> childs, String catDescr, int goodsCount, String catId, String catName, String catUrl) {
        this.catImg = catImg;
        this.childs = childs;
        this.catDescr = catDescr;
        this.goodsCount = goodsCount;
        this.catId = catId;
        this.catName = catName;
        this.catUrl = catUrl;
    }

    @SerializedName("cat_img")
    @Expose
    private String catImg;
    @SerializedName("childs")
    @Expose
    private List<Child> childs = null;
    @SerializedName("cat_descr")
    @Expose
    private String catDescr;

    @SerializedName("goods_count")
    @Expose
    private int goodsCount;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("cat_url")
    @Expose
    private String catUrl;

    public String getCatImg() {
        return catImg;
    }

    public void setCatImg(String catImg) {
        this.catImg = catImg;
    }

    public List<Child> getChilds() {
        return childs;
    }

    public void setChilds(List<Child> childs) {
        this.childs = childs;
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

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

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

}