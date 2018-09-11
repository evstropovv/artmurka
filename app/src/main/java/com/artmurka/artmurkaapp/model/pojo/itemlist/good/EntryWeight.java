
package com.artmurka.artmurkaapp.model.pojo.itemlist.good;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryWeight {

    @SerializedName("weight_raw")
    @Expose
    private Integer weightRaw;
    @SerializedName("weight")
    @Expose
    private String weight;

    public Integer getWeightRaw() {
        return weightRaw;
    }

    public void setWeightRaw(Integer weightRaw) {
        this.weightRaw = weightRaw;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

}
