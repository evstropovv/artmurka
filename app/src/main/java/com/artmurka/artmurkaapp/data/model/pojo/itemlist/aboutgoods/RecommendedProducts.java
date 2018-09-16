
package com.artmurka.artmurkaapp.data.model.pojo.itemlist.aboutgoods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecommendedProducts {

    @SerializedName("recomm_products")
    @Expose
    private RecommProducts recommProducts;
    @SerializedName("present")
    @Expose
    private int present;

    public RecommProducts getRecommProducts() {
        return recommProducts;
    }

    public void setRecommProducts(RecommProducts recommProducts) {
        this.recommProducts = recommProducts;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

}
