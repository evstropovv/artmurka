
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Discount {

    @SerializedName("sum")
    @Expose
    private String sum;

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

}
