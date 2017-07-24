package com.artmurka.artmurkaapp.Model.Modules;

import android.util.Log;

import com.artmurka.artmurkaapp.Model.FormOrder.AdrAndEmail;
import com.artmurka.artmurkaapp.Model.InterfacesModel.ICheckoutRequest;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.CheckoutAllGoods;
import com.artmurka.artmurkaapp.Model.Retrofit.Success;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;

public class CheckoutRequest implements ICheckoutRequest {

    @Override
    public Call<CheckoutAllGoods> getCheckoutData() {
        UcozApiModule ucoz = new UcozApiModule();
        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();
        HashMap<String, String> confForRequest = ucoz.get("GET", "uapi/shop/checkout/", mapForUcozModule);

        return ApiModule.getClient().getCheckout(confForRequest);
    }

    @Override
    public Call<CheckoutAllGoods> recountCheckoutData(String position, String cnt) {
        UcozApiModule ucoz = new UcozApiModule();
        HashMap<String, String> mapForUcozModule = new HashMap<>();
        mapForUcozModule.put("mode", "recalc");
        mapForUcozModule.put("cnt_" + position, cnt);
        HashMap<String, String> confForRequest = ucoz.get("PUT", "uapi/shop/checkout/", mapForUcozModule);
        return ApiModule.getClient().recountCheckoutData(confForRequest);
    }


    @Override
    public Call<Success> postCheckout(String telephone, String msg, String email, String pay, String delivery) {
        UcozApiModule ucoz = new UcozApiModule();
        Map<String, String> mapForUcozModule = new TreeMap<>();
        mapForUcozModule.put("mode", "order");
        mapForUcozModule.put("payment_id", pay); //1 -
        mapForUcozModule.put("delivery_id", delivery);

//        private static final PercentEscaper percentEncoder = new PercentEscaper(
//                "-._~", false);
//        String st2 = msg.esc("^@(.+)$", "repl");
//        String st3 = email.replace("^@(.+)$", "repl");
        mapForUcozModule.put("fld1", telephone);
        mapForUcozModule.put("fld2", msg);
        mapForUcozModule.put("fld3", email);

        HashMap<String, String> confForRequest2 = ucoz.get("POST", "uapi/shop/checkout/", mapForUcozModule);
        Log.d("Log.d", new Gson().toJson(confForRequest2));
        return ApiModule.getClient().postCheckout(confForRequest2, "sdfsdf", "dsfsdf");

    }
}
