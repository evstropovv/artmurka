
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderHide {

    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("2")
    @Expose
    private String _2;
    @SerializedName("1")
    @Expose
    private String _1;

    public String get0() {
        return _0;
    }

    public void set0(String _0) {
        this._0 = _0;
    }

    public String get2() {
        return _2;
    }

    public void set2(String _2) {
        this._2 = _2;
    }

    public String get1() {
        return _1;
    }

    public void set1(String _1) {
        this._1 = _1;
    }

}
