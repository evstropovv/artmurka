
package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderWeight {

    @SerializedName("weight_raw")
    @Expose
    private Long weightRaw;
    @SerializedName("weight")
    @Expose
    private String weight;

    public Long getWeightRaw() {
        return weightRaw;
    }

    public void setWeightRaw(Long weightRaw) {
        this.weightRaw = weightRaw;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

}
