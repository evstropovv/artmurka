
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currency {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("rate")
    @Expose
    private String rate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

}
