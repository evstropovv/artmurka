
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.WishList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryPriceOld {

    @SerializedName("price")
    @Expose
    private Long price;
    @SerializedName("price_raw")
    @Expose
    private Long priceRaw;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getPriceRaw() {
        return priceRaw;
    }

    public void setPriceRaw(Long priceRaw) {
        this.priceRaw = priceRaw;
    }

}
