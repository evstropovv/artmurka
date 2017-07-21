package com.artmurka.artmurkaapp.Model.InterfacesModel;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.CheckoutAllGoods;

import java.util.HashMap;

import retrofit2.Call;

public interface ICheckoutRequest {
    Call<CheckoutAllGoods> getCheckoutData();
}
