
package com.artmurka.artmurkaapp.model.pojo.itemlist.itembasket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Over {

    @SerializedName("summ")
    @Expose
    private int summ;
    @SerializedName("summ_raw")
    @Expose
    private String summRaw;

    public int getSumm() {
        return summ;
    }

    public void setSumm(int summ) {
        this.summ = summ;
    }

    public String getSummRaw() {
        return summRaw;
    }

    public void setSummRaw(String summRaw) {
        this.summRaw = summRaw;
    }

}
