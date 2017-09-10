
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderTotal {

    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("total_raw")
    @Expose
    private Float totalRaw;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Float getTotalRaw() {
        return totalRaw;
    }

    public void setTotalRaw(Float totalRaw) {
        this.totalRaw = totalRaw;
    }

}
