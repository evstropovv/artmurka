
package com.artmurka.artmurkaapp.model.pojo.itemlist.orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Delivery {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("tax")
    @Expose
    private String tax;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

}
