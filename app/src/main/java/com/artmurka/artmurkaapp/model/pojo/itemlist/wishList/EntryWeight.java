
package com.artmurka.artmurkaapp.model.pojo.itemlist.wishList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryWeight {

    @SerializedName("weight_raw")
    @Expose
    private String weightRaw;
    @SerializedName("weight")
    @Expose
    private Long weight;

    public String getWeightRaw() {
        return weightRaw;
    }

    public void setWeightRaw(String weightRaw) {
        this.weightRaw = weightRaw;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

}
