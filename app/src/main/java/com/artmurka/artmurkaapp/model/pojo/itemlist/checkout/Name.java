
package com.artmurka.artmurkaapp.model.pojo.itemlist.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pos")
    @Expose
    private Long pos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPos() {
        return pos;
    }

    public void setPos(Long pos) {
        this.pos = pos;
    }

}
