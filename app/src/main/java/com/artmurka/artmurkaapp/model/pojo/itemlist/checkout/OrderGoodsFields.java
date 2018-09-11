
package com.artmurka.artmurkaapp.model.pojo.itemlist.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderGoodsFields {

    @SerializedName("sum")
    @Expose
    private Sum sum;
    @SerializedName("price")
    @Expose
    private Price price;
    @SerializedName("cnt")
    @Expose
    private Cnt cnt;
    @SerializedName("name")
    @Expose
    private Name name;

    public Sum getSum() {
        return sum;
    }

    public void setSum(Sum sum) {
        this.sum = sum;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Cnt getCnt() {
        return cnt;
    }

    public void setCnt(Cnt cnt) {
        this.cnt = cnt;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

}
