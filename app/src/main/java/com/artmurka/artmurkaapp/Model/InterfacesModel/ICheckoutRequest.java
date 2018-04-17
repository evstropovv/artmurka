package com.artmurka.artmurkaapp.Model.InterfacesModel;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.CheckoutAllGoods;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Categories.Success;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.CheckoutResponse.CheckoutResponse;

import io.reactivex.Observable;
import retrofit2.Call;

public interface ICheckoutRequest {
    Observable<CheckoutAllGoods> getCheckoutData();
    Call<CheckoutAllGoods> recountCheckoutData(String position, String cnt);
    Call<CheckoutResponse> postCheckout(String telephoneNumber, String adress, String email, String pay, String delivery);
}
