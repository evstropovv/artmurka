
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemList;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryCats {

    @SerializedName("cats")
    @Expose
    private List<Cat> cats = null;
    @SerializedName("num_cats")
    @Expose
    private int numCats;

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public int getNumCats() {
        return numCats;
    }

    public void setNumCats(int numCats) {
        this.numCats = numCats;
    }

}
