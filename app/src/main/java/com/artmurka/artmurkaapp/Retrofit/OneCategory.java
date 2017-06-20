package com.artmurka.artmurkaapp.Retrofit;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OneCategory {

    @SerializedName("cat_descr")
    @Expose
    private String catDescr;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("cat_url")
    @Expose
    private String catUrl;
    @SerializedName("cat_img")
    @Expose
    private String catImg;
    @SerializedName("childs")
    @Expose
    private String childs;
    @SerializedName("goods_count")
    @Expose
    private int goodsCount;
    @SerializedName("cat_id")
    @Expose
    private String catId;

    public String getCatDescr() {
        return catDescr;
    }

    public String getCatName() {
        return catName;
    }

    public String getCatUrl() {
        return catUrl;
    }


    public String getCatImg() {
        return catImg;
    }

    public String getChilds() {
        return childs;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public String getCatId() {
        return catId;
    }

}