
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryCat {

    @SerializedName("shop_url")
    @Expose
    private String shopUrl;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
