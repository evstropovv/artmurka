
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryAssoc {

    @SerializedName("2")
    @Expose
    private Long _2;
    @SerializedName("1")
    @Expose
    private Long _1;

    public Long get2() {
        return _2;
    }

    public void set2(Long _2) {
        this._2 = _2;
    }

    public Long get1() {
        return _1;
    }

    public void set1(Long _1) {
        this._1 = _1;
    }

}
