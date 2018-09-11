package com.artmurka.artmurkaapp.model.interfacesmodel;

import com.artmurka.artmurkaapp.model.pojo.itemlist.orders.Orders;

import retrofit2.Call;



public interface IOrderRequest {
    Call<Orders> getOrders();
}
