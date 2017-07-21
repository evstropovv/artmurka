
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Success {

    @SerializedName("order_data")
    @Expose
    private OrderData orderData;
    @SerializedName("current_delivery_id")
    @Expose
    private Long currentDeliveryId;
    @SerializedName("current_payment_id")
    @Expose
    private Long currentPaymentId;
    @SerializedName("num_entries")
    @Expose
    private Long numEntries;
    @SerializedName("order_content")
    @Expose
    private OrderContent orderContent;

    public OrderData getOrderData() {
        return orderData;
    }

    public void setOrderData(OrderData orderData) {
        this.orderData = orderData;
    }

    public Long getCurrentDeliveryId() {
        return currentDeliveryId;
    }

    public void setCurrentDeliveryId(Long currentDeliveryId) {
        this.currentDeliveryId = currentDeliveryId;
    }

    public Long getCurrentPaymentId() {
        return currentPaymentId;
    }

    public void setCurrentPaymentId(Long currentPaymentId) {
        this.currentPaymentId = currentPaymentId;
    }

    public Long getNumEntries() {
        return numEntries;
    }

    public void setNumEntries(Long numEntries) {
        this.numEntries = numEntries;
    }

    public OrderContent getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(OrderContent orderContent) {
        this.orderContent = orderContent;
    }

}
