package com.artmurka.artmurkaapp.Model.Modules;

import com.artmurka.artmurkaapp.Model.InterfacesModel.ICheckoutRequest;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.CheckoutAllGoods;

import java.util.HashMap;

import retrofit2.Call;

public class CheckoutRequest implements ICheckoutRequest {

    @Override
    public Call<CheckoutAllGoods> getCheckoutData() {
        UcozApiModule ucoz = new UcozApiModule();
        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();
        mapForUcozModule.put("method", "GET");
        mapForUcozModule.put("url", "uapi/shop/checkout/");
        HashMap<String, String> confForRequest = ucoz.get(mapForUcozModule);

        return ApiModule.getClient().getCheckout(confForRequest);
    }
}
