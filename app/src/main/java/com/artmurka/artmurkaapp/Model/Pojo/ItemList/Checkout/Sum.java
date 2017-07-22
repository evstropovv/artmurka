
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sum {

    @SerializedName("sum_raw")
    @Expose
    private Long sumRaw;
    @SerializedName("name")
    @Expose
    private String sum;

    public Long getSumRaw() {
        return sumRaw;
    }

    public String getSum() {
        return sum;
    }
    public void setSum(String sum){
        this.sum = sum;
    }

    public void setSumRaw(Long sumRaw){
        this.sumRaw = sumRaw;
    }



}
