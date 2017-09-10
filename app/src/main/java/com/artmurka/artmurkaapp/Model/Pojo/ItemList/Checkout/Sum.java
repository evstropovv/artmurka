
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sum {

    @SerializedName("sum_raw")
    @Expose
    private Float sumRaw;
    @SerializedName("name")
    @Expose
    private String sum;

    public Float getSumRaw() {
        return sumRaw;
    }

    public String getSum() {
        return sum;
    }
    public void setSum(String sum){
        this.sum = sum;
    }

    public void setSumRaw(Float sumRaw){
        this.sumRaw = sumRaw;
    }

}
