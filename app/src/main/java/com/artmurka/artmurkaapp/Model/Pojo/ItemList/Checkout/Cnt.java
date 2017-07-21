
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cnt {

    @SerializedName("pos")
    @Expose
    private Long pos;
    @SerializedName("name")
    @Expose
    private String name;

    public Long getPos() {
        return pos;
    }

    public void setPos(Long pos) {
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
