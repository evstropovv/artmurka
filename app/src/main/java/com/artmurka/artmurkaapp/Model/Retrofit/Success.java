package com.artmurka.artmurkaapp.Model.Retrofit;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Success implements Parcelable {

    @SerializedName("cat_id")
    @Expose
    private String catId;

    @SerializedName("msg")
    @Expose
    private String msg;

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

    @SerializedName("childs")
    @Expose
    private String childs;

    @SerializedName("cat_url")
    @Expose
    private String catUrl;

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.catId);
        dest.writeString(this.catDescr);
        dest.writeInt(this.goodsCount);
        dest.writeString(this.catImg);
        dest.writeString(this.catName);

        dest.writeString(this.childs);
        dest.writeString(this.catUrl);
    }

    public Success() {
    }

    protected Success(Parcel in) {
        this.catId = in.readString();
        this.catDescr = in.readString();
        this.goodsCount = in.readInt();
        this.catImg = in.readString();
        this.catName = in.readString();

        this.childs = in.readString();
        this.catUrl = in.readString();
    }

    public static final Parcelable.Creator<Success> CREATOR = new Parcelable.Creator<Success>() {
        @Override
        public Success createFromParcel(Parcel source) {
            return new Success(source);
        }

        @Override
        public Success[] newArray(int size) {
            return new Success[size];
        }
    };
}