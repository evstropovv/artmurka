
package com.artmurka.artmurkaapp.Model.Pojo.ItemList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryPrice {

    @SerializedName("price_raw")
    @Expose
    private int priceRaw;
    @SerializedName("price")
    @Expose
    private String price;

    public int getPriceRaw() {
        return priceRaw;
    }

    public void setPriceRaw(int priceRaw) {
        this.priceRaw = priceRaw;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
