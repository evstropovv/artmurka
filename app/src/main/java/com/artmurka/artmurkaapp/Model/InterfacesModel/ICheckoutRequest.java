package com.artmurka.artmurkaapp.Model.InterfacesModel;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.CheckoutAllGoods;
import com.artmurka.artmurkaapp.Model.Retrofit.Success;

import java.util.HashMap;

import retrofit2.Call;

public interface ICheckoutRequest {
    Call<CheckoutAllGoods> getCheckoutData();
    Call<CheckoutAllGoods> recountCheckoutData(String position, String cnt);
    Call<Success> postCheckout(String telephoneNumber, String message, String email);
}
