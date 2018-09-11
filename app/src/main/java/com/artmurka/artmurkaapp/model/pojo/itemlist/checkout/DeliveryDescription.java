package com.artmurka.artmurkaapp.model.pojo.itemlist.checkout;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryDescription {

    @SerializedName("dscr")
    @Expose
    private String dscr;
    @SerializedName("name")
    @Expose
    private String name;

    public String getDscr() {
        return dscr;
    }

    public void setDscr(String dscr) {
        this.dscr = dscr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
