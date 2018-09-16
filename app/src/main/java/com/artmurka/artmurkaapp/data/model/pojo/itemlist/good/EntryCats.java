
package com.artmurka.artmurkaapp.data.model.pojo.itemlist.good;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryCats {

    @SerializedName("cats")
    @Expose
    private List<Cat> cats = null;
    @SerializedName("num_cats")
    @Expose
    private Integer numCats;

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public Integer getNumCats() {
        return numCats;
    }

    public void setNumCats(Integer numCats) {
        this.numCats = numCats;
    }

}
