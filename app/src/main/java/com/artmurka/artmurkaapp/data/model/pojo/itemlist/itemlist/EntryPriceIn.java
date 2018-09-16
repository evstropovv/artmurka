
package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryPriceIn {

    @SerializedName("price_raw")
    @Expose
    private String priceRaw;
    @SerializedName("price")
    @Expose
    private String price;

    public String getPriceRaw() {
        return priceRaw;
    }

    public void setPriceRaw(String priceRaw) {
        this.priceRaw = priceRaw;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
