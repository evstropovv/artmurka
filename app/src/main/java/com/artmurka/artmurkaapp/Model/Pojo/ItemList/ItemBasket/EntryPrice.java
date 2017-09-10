
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemBasket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryPrice {

    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("price_raw")
    @Expose
    private String priceRaw;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceRaw() {
        return priceRaw;
    }

    public void setPriceRaw(String priceRaw) {
        this.priceRaw = priceRaw;
    }

}
