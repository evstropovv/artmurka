package com.artmurka.artmurkaapp.presenter;

import com.artmurka.artmurkaapp.model.interfacesmodel.IOrderRequest;
import com.artmurka.artmurkaapp.model.modules.OrdersRequests;
import com.artmurka.artmurkaapp.model.pojo.itemlist.orders.Orders;
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IOrderPresenter;
import com.artmurka.artmurkaapp.views.fragments.interfaces.IOrderFragment;

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
