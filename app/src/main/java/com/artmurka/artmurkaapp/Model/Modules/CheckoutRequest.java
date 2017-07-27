package com.artmurka.artmurkaapp.Model.Modules;

import android.support.annotation.NonNull;
import android.util.Log;

import com.artmurka.artmurkaapp.Model.FormOrder.AdrAndEmail;
import com.artmurka.artmurkaapp.Model.InterfacesModel.ICheckoutRequest;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.CheckoutAllGoods;
import com.artmurka.artmurkaapp.Model.Retrofit.Success;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.RequestBody;
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
        mapForUcozModule.put("fld2", email);
        mapForUcozModule.put("fld3", msg);

//        try{
//            mapForUcozModule.put("fld2", URLEncoder.encode(newEmail, "UTF-8"));
//            mapForUcozModule.put("fld3", URLEncoder.encode(newMsg, "UTF-8"));
//        } catch (UnsupportedEncodingException e){}



        HashMap<String, String> confForRequest2 = ucoz.get("POST", "uapi/shop/checkout/", mapForUcozModule);
        Log.d("Log.d", new Gson().toJson(confForRequest2));


        HashMap<String, RequestBody> reqBodyMap = new HashMap<>();
        reqBodyMap.put("oauth_signature", createPartFromString(confForRequest2.get("oauth_signature")));
        reqBodyMap.put("oauth_signature_method", createPartFromString(confForRequest2.get("oauth_signature_method")));
        reqBodyMap.put("oauth_version", createPartFromString(confForRequest2.get("oauth_version")));
        reqBodyMap.put("oauth_consumer_key", createPartFromString(confForRequest2.get("oauth_consumer_key")));
        reqBodyMap.put("oauth_token", createPartFromString(confForRequest2.get("oauth_token")));
        reqBodyMap.put("oauth_nonce", createPartFromString(confForRequest2.get("oauth_nonce")));
        reqBodyMap.put("oauth_timestamp", createPartFromString(confForRequest2.get("oauth_timestamp")));

        reqBodyMap.put("mode", createPartFromString("order"));
        reqBodyMap.put("payment_id", createPartFromString(pay));
        reqBodyMap.put("delivery_id", createPartFromString(delivery));
        reqBodyMap.put("fld1", createPartFromString(telephone));
//        reqBodyMap.put("fld2", createPartFromString(email));
//        reqBodyMap.put("fld3", createPartFromString(msg));

        return ApiModule.getClient().postCheckout(confForRequest2);
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(okhttp3.MultipartBody.FORM, descriptionString);
    }
}
