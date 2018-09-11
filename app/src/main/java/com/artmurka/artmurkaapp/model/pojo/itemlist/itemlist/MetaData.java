
package com.artmurka.artmurkaapp.model.pojo.itemlist.itemlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetaData {

    @SerializedName("META_DESCRIPTION")
    @Expose
    private String mETADESCRIPTION;
    @SerializedName("META_TITLE")
    @Expose
    private String mETATITLE;

    public String getMETADESCRIPTION() {
        return mETADESCRIPTION;
    }

    public void setMETADESCRIPTION(String mETADESCRIPTION) {
        this.mETADESCRIPTION = mETADESCRIPTION;
    }

    public String getMETATITLE() {
        return mETATITLE;
    }

    public void setMETATITLE(String mETATITLE) {
        this.mETATITLE = mETATITLE;
    }

}
