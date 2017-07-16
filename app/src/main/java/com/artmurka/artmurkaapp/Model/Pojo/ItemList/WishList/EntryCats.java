
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.WishList;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryCats {

    @SerializedName("cats")
    @Expose
    private List<Cat> cats = null;
    @SerializedName("num_cats")
    @Expose
    private Long numCats;

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public Long getNumCats() {
        return numCats;
    }

    public void setNumCats(Long numCats) {
        this.numCats = numCats;
    }

}
