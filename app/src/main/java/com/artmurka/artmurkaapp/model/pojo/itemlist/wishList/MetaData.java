
package com.artmurka.artmurkaapp.model.pojo.itemlist.wishList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetaData {

    @SerializedName("META_TITLE")
    @Expose
    private String mETATITLE;
    @SerializedName("META_DESCRIPTION")
    @Expose
    private String mETADESCRIPTION;

    public String getMETATITLE() {
        return mETATITLE;
    }

    public void setMETATITLE(String mETATITLE) {
        this.mETATITLE = mETATITLE;
    }

    public String getMETADESCRIPTION() {
        return mETADESCRIPTION;
    }

    public void setMETADESCRIPTION(String mETADESCRIPTION) {
        this.mETADESCRIPTION = mETADESCRIPTION;
    }

}
