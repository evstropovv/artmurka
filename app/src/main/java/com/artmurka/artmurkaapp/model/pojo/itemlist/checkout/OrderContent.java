
package com.artmurka.artmurkaapp.model.pojo.itemlist.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class OrderContent {

    @SerializedName("order_goods_fields")
    @Expose
    private OrderGoodsFields orderGoodsFields;
    @SerializedName("order_goods")
    @Expose
    private HashMap<String, OrderDesc> orderGoods;

    public OrderGoodsFields getOrderGoodsFields() {
        return orderGoodsFields;
    }

    public void setOrderGoodsFields(OrderGoodsFields orderGoodsFields) {
        this.orderGoodsFields = orderGoodsFields;
    }

    public HashMap<String, OrderDesc> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(HashMap<String, OrderDesc> orderGoods) {
        this.orderGoods = orderGoods;
    }

}
