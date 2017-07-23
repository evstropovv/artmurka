package com.artmurka.artmurkaapp.Model.InterfacesModel;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Orders.Orders;

import retrofit2.Call;



public interface IOrderRequest {
    Call<Orders> getOrders();
}
