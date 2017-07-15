
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryWeight {

    @SerializedName("weight_raw")
    @Expose
    private int weightRaw;
    @SerializedName("weight")
    @Expose
    private String weight;

    public int getWeightRaw() {
        return weightRaw;
    }

    public void setWeightRaw(int weightRaw) {
        this.weightRaw = weightRaw;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

}
