
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemBasket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Summ {

    @SerializedName("summ")
    @Expose
    private String summ;
    @SerializedName("summ_raw")
    @Expose
    private int summRaw;

    public String getSumm() {
        return summ;
    }

    public void setSumm(String summ) {
        this.summ = summ;
    }

    public int getSummRaw() {
        return summRaw;
    }

    public void setSummRaw(int summRaw) {
        this.summRaw = summRaw;
    }

}
