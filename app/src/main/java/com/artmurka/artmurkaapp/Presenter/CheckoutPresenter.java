package com.artmurka.artmurkaapp.Presenter;

import android.util.Log;
import android.widget.Toast;

import com.artmurka.artmurkaapp.Model.InterfacesModel.ICheckoutRequest;
import com.artmurka.artmurkaapp.Model.Modules.CheckoutRequest;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.CheckoutAllGoods;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.OrderDesc;

import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.ICheckoutPresenter;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.ICheckoutFragment;
import com.artmurka.artmurkaapp.Model.Retrofit.Success;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutPresenter implements ICheckoutPresenter {
    private ICheckoutFragment fragment;
    private ICheckoutRequest request;

    public CheckoutPresenter(ICheckoutFragment fragment) {
        this.fragment = fragment;
        request = new CheckoutRequest();
    }

    @Override
    public void getData() {
        Call<CheckoutAllGoods> call = request.getCheckoutData();
        call.enqueue(new Callback<CheckoutAllGoods>() {
            @Override
            public void onResponse(Call<CheckoutAllGoods> call, Response<CheckoutAllGoods> response) {
                Log.d("Log.d", new Gson().toJson(response.body()));
                fragment.showCheckout(getList(response.body().getSuccess().getOrderContent().getOrderGoods()));
                fragment.refreshSumPrice(response.body().getSuccess().getOrderData().getOrderAmount().getAmountRaw().toString() + " грн");

            }

            @Override
            public void onFailure(Call<CheckoutAllGoods> call, Throwable t) {

            }
        });
    }

    @Override
    public void postCheckout(String telephone, String message, String email) {
        Call<Success> call = request.postCheckout(telephone, message, email);
        call.enqueue(new Callback<Success>() {
            @Override
            public void onResponse(Call<Success> call, Response<Success> response) {
                Log.d("Log.d", new Gson().toJson(response.body()));
                fragment.showOrderIsProcessed(response.body().getMsg());

            }

            @Override
            public void onFailure(Call<Success> call, Throwable t) {

            }
        });

    }

    private List<OrderDesc> getList(HashMap<String, OrderDesc> map) {
        List<OrderDesc> answerList = new ArrayList<>();
        for (String key : map.keySet()) {
            OrderDesc desc = map.get(key);
            desc.setOrderPosition(key);
            answerList.add(desc);
        }
        return answerList;
    }


}
