
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderAmount {

    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("amount_raw")
    @Expose
    private Long amountRaw;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Long getAmountRaw() {
        return amountRaw;
    }

    public void setAmountRaw(Long amountRaw) {
        this.amountRaw = amountRaw;
    }

}
