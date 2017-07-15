
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryPriceOld {

    @SerializedName("price_raw")
    @Expose
    private int priceRaw;
    @SerializedName("price")
    @Expose
    private int price;

    public int getPriceRaw() {
        return priceRaw;
    }

    public void setPriceRaw(int priceRaw) {
        this.priceRaw = priceRaw;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
