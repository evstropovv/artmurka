
package com.artmurka.artmurkaapp.data.model.pojo.itemlist.good;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecommendedProducts {

    @SerializedName("recomm_products")
    @Expose
    private RecommProducts recommProducts;
    @SerializedName("present")
    @Expose
    private Integer present;

    public RecommProducts getRecommProducts() {
        return recommProducts;
    }

    public void setRecommProducts(RecommProducts recommProducts) {
        this.recommProducts = recommProducts;
    }

    public Integer getPresent() {
        return present;
    }

    public void setPresent(Integer present) {
        this.present = present;
    }

}
