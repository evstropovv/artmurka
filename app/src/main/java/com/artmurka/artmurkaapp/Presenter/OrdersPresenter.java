package com.artmurka.artmurkaapp.Presenter;

import android.util.Log;

import com.artmurka.artmurkaapp.Model.InterfacesModel.IOrderRequest;
import com.artmurka.artmurkaapp.Model.Modules.OrdersRequests;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Orders.Orders;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.IOrderPresenter;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.IOrderFragment;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersPresenter implements IOrderPresenter{
    IOrderFragment fragment;

    public OrdersPresenter(IOrderFragment fragment){
        this.fragment = fragment;

    }

    @Override
    public void getOrders() {
        IOrderRequest request = new OrdersRequests();
        Call<Orders> orders = request.getOrders();
        orders.enqueue(new Callback<Orders>() {
            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {
                fragment.showOrders(response.body());
            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {

            }
        });
    }
}
