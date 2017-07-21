
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDiscount {

    @SerializedName("discount")
    @Expose
    private Long discount;
    @SerializedName("discount_raw")
    @Expose
    private Long discountRaw;

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public Long getDiscountRaw() {
        return discountRaw;
    }

    public void setDiscountRaw(Long discountRaw) {
        this.discountRaw = discountRaw;
    }

}
