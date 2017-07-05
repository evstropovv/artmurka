
package com.artmurka.artmurkaapp.Model.Pojo.ItemList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryWeight {

    @SerializedName("weight_raw")
    @Expose
    private String weightRaw;
    @SerializedName("weight")
    @Expose
    private int weight;

    public String getWeightRaw() {
        return weightRaw;
    }

    public void setWeightRaw(String weightRaw) {
        this.weightRaw = weightRaw;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
