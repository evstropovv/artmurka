
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemBasket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Summ {

    @SerializedName("summ")
    @Expose
    private String summ;
    @SerializedName("summ_raw")
    @Expose
    private String summRaw;

    public String getSumm() {
        return summ;
    }

    public void setSumm(String summ) {
        this.summ = summ;
    }

    public String getSummRaw() {
        return summRaw;
    }

    public void setSummRaw(String summRaw) {
        this.summRaw = summRaw;
    }

}
