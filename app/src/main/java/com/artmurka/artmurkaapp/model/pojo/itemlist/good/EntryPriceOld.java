
package com.artmurka.artmurkaapp.model.pojo.itemlist.good;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryPriceOld {

    @SerializedName("price_raw")
    @Expose
    private Integer priceRaw;
    @SerializedName("price")
    @Expose
    private Integer price;

    public Integer getPriceRaw() {
        return priceRaw;
    }

    public void setPriceRaw(Integer priceRaw) {
        this.priceRaw = priceRaw;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
