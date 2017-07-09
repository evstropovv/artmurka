
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemBasket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryPrice {

    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("price_raw")
    @Expose
    private int priceRaw;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getPriceRaw() {
        return priceRaw;
    }

    public void setPriceRaw(int priceRaw) {
        this.priceRaw = priceRaw;
    }

}
