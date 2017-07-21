
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderData {

    @SerializedName("order_info")
    @Expose
    private String orderInfo;
    @SerializedName("order_tax")
    @Expose
    private OrderTax orderTax;
    @SerializedName("order_vat")
    @Expose
    private OrderVat orderVat;
    @SerializedName("order_total")
    @Expose
    private OrderTotal orderTotal;
    @SerializedName("order_notice")
    @Expose
    private OrderNotice orderNotice;
    @SerializedName("order_weight")
    @Expose
    private OrderWeight orderWeight;
    @SerializedName("order_amount")
    @Expose
    private OrderAmount orderAmount;
    @SerializedName("order_topay")
    @Expose
    private OrderTopay orderTopay;
    @SerializedName("order_uid")
    @Expose
    private String orderUid;
    @SerializedName("order_discount")
    @Expose
    private OrderDiscount orderDiscount;

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public OrderTax getOrderTax() {
        return orderTax;
    }

    public void setOrderTax(OrderTax orderTax) {
        this.orderTax = orderTax;
    }

    public OrderVat getOrderVat() {
        return orderVat;
    }

    public void setOrderVat(OrderVat orderVat) {
        this.orderVat = orderVat;
    }

    public OrderTotal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(OrderTotal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public OrderNotice getOrderNotice() {
        return orderNotice;
    }

    public void setOrderNotice(OrderNotice orderNotice) {
        this.orderNotice = orderNotice;
    }

    public OrderWeight getOrderWeight() {
        return orderWeight;
    }

    public void setOrderWeight(OrderWeight orderWeight) {
        this.orderWeight = orderWeight;
    }

    public OrderAmount getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(OrderAmount orderAmount) {
        this.orderAmount = orderAmount;
    }

    public OrderTopay getOrderTopay() {
        return orderTopay;
    }

    public void setOrderTopay(OrderTopay orderTopay) {
        this.orderTopay = orderTopay;
    }

    public String getOrderUid() {
        return orderUid;
    }

    public void setOrderUid(String orderUid) {
        this.orderUid = orderUid;
    }

    public OrderDiscount getOrderDiscount() {
        return orderDiscount;
    }

    public void setOrderDiscount(OrderDiscount orderDiscount) {
        this.orderDiscount = orderDiscount;
    }

}
