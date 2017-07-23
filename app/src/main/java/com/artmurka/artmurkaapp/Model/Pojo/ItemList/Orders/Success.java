
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Orders;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Success {

    @SerializedName("page")
    @Expose
    private Long page;
    @SerializedName("total")
    @Expose
    private Long total;
    @SerializedName("pages_cnt")
    @Expose
    private Long pagesCnt;
    @SerializedName("order_hide")
    @Expose
    private OrderHide orderHide;
    @SerializedName("count")
    @Expose
    private String count;
    @SerializedName("order_uids")
    @Expose
    private List<String> orderUids = null;
    @SerializedName("per_page")
    @Expose
    private String perPage;
    @SerializedName("profit")
    @Expose
    private String profit;
    @SerializedName("orders")
    @Expose
    private List<Order> orders = null;
    @SerializedName("order_fields")
    @Expose
    private OrderFields orderFields;
    @SerializedName("order_status")
    @Expose
    private OrderStatus orderStatus;

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPagesCnt() {
        return pagesCnt;
    }

    public void setPagesCnt(Long pagesCnt) {
        this.pagesCnt = pagesCnt;
    }

    public OrderHide getOrderHide() {
        return orderHide;
    }

    public void setOrderHide(OrderHide orderHide) {
        this.orderHide = orderHide;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<String> getOrderUids() {
        return orderUids;
    }

    public void setOrderUids(List<String> orderUids) {
        this.orderUids = orderUids;
    }

    public String getPerPage() {
        return perPage;
    }

    public void setPerPage(String perPage) {
        this.perPage = perPage;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public OrderFields getOrderFields() {
        return orderFields;
    }

    public void setOrderFields(OrderFields orderFields) {
        this.orderFields = orderFields;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

}
