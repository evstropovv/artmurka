package com.artmurka.artmurkaapp.Model.Modules;

import com.artmurka.artmurkaapp.Model.InterfacesModel.ICheckoutRequest;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.CheckoutAllGoods;
import com.artmurka.artmurkaapp.Model.Retrofit.Success;

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
        HashMap<String, String> mapForUcozModule = new HashMap<>();
        mapForUcozModule.put("mode", "recalc");
        mapForUcozModule.put("cnt_"+position, cnt);
        HashMap<String, String> confForRequest = ucoz.get("PUT", "uapi/shop/checkout/", mapForUcozModule);
        return ApiModule.getClient().recountCheckoutData(confForRequest);
    }

    @Override
    public Call<Success> postCheckout(String telephone, String msg, String email) {
        UcozApiModule ucoz = new UcozApiModule();
        HashMap<String, String> mapForUcozModule = new HashMap<>();
        mapForUcozModule.put("mode", "order");
        mapForUcozModule.put("payment_id", "1"); //1 -
        mapForUcozModule.put("delivery_id", "2");
        mapForUcozModule.put("fld1", telephone);
        mapForUcozModule.put("fld2", msg);
        mapForUcozModule.put("fld3", email);

        HashMap<String, String> confForRequest = ucoz.get("POST", "uapi/shop/checkout/", mapForUcozModule);

        return ApiModule.getClient().postCheckout(confForRequest);
    }
}
