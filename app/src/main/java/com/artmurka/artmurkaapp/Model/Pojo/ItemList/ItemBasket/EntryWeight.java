
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemBasket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryWeight {

    @SerializedName("weight")
    @Expose
    private int weight;
    @SerializedName("weight_raw")
    @Expose
    private int weightRaw;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeightRaw() {
        return weightRaw;
    }

    public void setWeightRaw(int weightRaw) {
        this.weightRaw = weightRaw;
    }

}
