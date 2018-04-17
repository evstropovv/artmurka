package com.artmurka.artmurkaapp.Model.Modules;

import android.util.Log;

import com.artmurka.artmurkaapp.Model.InterfacesModel.ICheckoutRequest;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.CheckoutAllGoods;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Categories.Success;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.CheckoutResponse.CheckoutResponse;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import io.reactivex.Observable;
import retrofit2.Call;

public class CheckoutRequest implements ICheckoutRequest {

    @Override
    public Observable<CheckoutAllGoods> getCheckoutData() {
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
    public Call<CheckoutResponse> postCheckout(String telephone, String msg, String email, String pay, String delivery) {
        UcozApiModule ucoz = new UcozApiModule();
        Map<String, String> mapForUcozModule = new TreeMap<>();
        mapForUcozModule.put("mode", "order");
        mapForUcozModule.put("payment_id", pay); //1 -
        mapForUcozModule.put("delivery_id", delivery);
        mapForUcozModule.put("fld1", telephone);

        String encodeEmail = null;
        String encodeMsg = null;
        String msgTrim =msg.replace(" ",""); //убираем пробелы
        try {
            encodeEmail = URLEncoder.encode(email, "UTF-8");
            encodeMsg = URLEncoder.encode(msgTrim, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mapForUcozModule.put("fld2", encodeMsg);
        mapForUcozModule.put("fld3", encodeEmail);

        HashMap<String, String> confForRequest2 = ucoz.get("POST", "uapi/shop/checkout/", mapForUcozModule);
        Log.d("Log.d", new Gson().toJson(confForRequest2));


        HashMap<String, String> reqBodyMap = new HashMap<>();
        reqBodyMap.put("oauth_signature", (confForRequest2.get("oauth_signature")));
        reqBodyMap.put("oauth_signature_method", (confForRequest2.get("oauth_signature_method")));
        reqBodyMap.put("oauth_version", (confForRequest2.get("oauth_version")));
        reqBodyMap.put("oauth_consumer_key", (confForRequest2.get("oauth_consumer_key")));
        reqBodyMap.put("oauth_token", (confForRequest2.get("oauth_token")));
        reqBodyMap.put("oauth_nonce", confForRequest2.get("oauth_nonce"));
        reqBodyMap.put("oauth_timestamp", confForRequest2.get("oauth_timestamp"));

        reqBodyMap.put("mode", "order");
        reqBodyMap.put("payment_id", pay);
        reqBodyMap.put("delivery_id", delivery);

        reqBodyMap.put("fld1", telephone);
        reqBodyMap.put("fld2", encodeMsg);
        reqBodyMap.put("fld3", encodeEmail);

        return ApiModule.getClient().postCheckout(confForRequest2);
    }
}