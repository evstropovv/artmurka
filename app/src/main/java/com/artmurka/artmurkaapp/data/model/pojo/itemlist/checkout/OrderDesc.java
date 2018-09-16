
package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDesc {

    @SerializedName("cnt")
    @Expose
    private String cnt;
    @SerializedName("goods_id")
    @Expose
    private String goodsId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sum")
    @Expose
    private Sum sum;
    @SerializedName("warning")
    @Expose
    private String warning;
    @SerializedName("price")
    @Expose
    private Price price;

    private String orderPosition;

    public String getOrderPosition(){
        return orderPosition;
    }
    public void setOrderPosition(String orderPosition){
        this.orderPosition = orderPosition;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sum getSum() {
        return sum;
    }

    public void setSum(Sum sum) {
        this.sum = sum;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

}
