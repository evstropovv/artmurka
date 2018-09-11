
package com.artmurka.artmurkaapp.model.pojo.itemlist.good;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryPriceVat {

    @SerializedName("price_raw")
    @Expose
    private Integer priceRaw;
    @SerializedName("price")
    @Expose
    private String price;

    public Integer getPriceRaw() {
        return priceRaw;
    }

    public void setPriceRaw(Integer priceRaw) {
        this.priceRaw = priceRaw;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
