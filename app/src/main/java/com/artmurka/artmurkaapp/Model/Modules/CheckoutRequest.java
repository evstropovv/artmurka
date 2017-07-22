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
        HashMap<String, String> confForRequest = ucoz.get("GET","uapi/shop/checkout/",mapForUcozModule);

        return ApiModule.getClient().getCheckout(confForRequest);
    }

    @Override
    public Call<CheckoutAllGoods> recountCheckoutData(String position, String cnt) {
        UcozApiModule ucoz = new UcozApiModule();
        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();
        mapForUcozModule.put("mode", "recalc");
        mapForUcozModule.put("cnt_"+position, cnt);
        HashMap<String, String> confForRequest = ucoz.get("PUT", "uapi/shop/checkout/", mapForUcozModule);
        return ApiModule.getClient().getCheckout(confForRequest);
    }
}
