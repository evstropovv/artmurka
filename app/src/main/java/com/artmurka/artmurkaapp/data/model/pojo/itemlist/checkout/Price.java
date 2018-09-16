
package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("price_raw")
    @Expose
    private Float priceRaw;

    public String getPrice() {
        return price;
    }

    public Float getPriceRaw() {
        return priceRaw;
    }

}
