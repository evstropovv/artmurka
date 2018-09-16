
package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderAmount {

    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("amount_raw")
    @Expose
    private Float amountRaw;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Float getAmountRaw() {
        return amountRaw;
    }

    public void setAmountRaw(Float amountRaw) {
        this.amountRaw = amountRaw;
    }

}
