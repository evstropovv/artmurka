
package com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Slist {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("field")
    @Expose
    private String field;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

}
