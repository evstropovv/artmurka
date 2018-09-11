package com.artmurka.artmurkaapp.model.interfacesmodel;

import com.artmurka.artmurkaapp.model.pojo.itemlist.checkout.CheckoutAllGoods;
import com.artmurka.artmurkaapp.model.pojo.itemlist.checkoutresponse.CheckoutResponse;

import io.reactivex.Observable;
import retrofit2.Call;

public interface ICheckoutRequest {
    Observable<CheckoutAllGoods> getCheckoutData();
    Call<CheckoutAllGoods> recountCheckoutData(String position, String cnt);
    Call<CheckoutResponse> postCheckout(String telephoneNumber, String adress, String email, String pay, String delivery);
}
