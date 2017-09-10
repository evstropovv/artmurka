
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Good;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryPriceVatEval {

    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("price_raw")
    @Expose
    private Integer priceRaw;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getPriceRaw() {
        return priceRaw;
    }

    public void setPriceRaw(Integer priceRaw) {
        this.priceRaw = priceRaw;
    }

}
