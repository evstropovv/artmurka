package com.artmurka.artmurkaapp.Modules;

import com.artmurka.artmurkaapp.Retrofit.Example;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Вася on 29.06.2017.
 */

public class AllRequestOvservers {


    public Observable<Example> getCategories(){
        UcozApiModule ucoz = new UcozApiModule();

        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();
        mapForUcozModule.put("page", "categories");
        mapForUcozModule.put("method", "GET");
        mapForUcozModule.put("url", "uapi/shop/request");

        //Getting all token for autorization.
        HashMap<String, String> confForRequest = ucoz.get(mapForUcozModule);

        return ApiModule.getClient().getShopCategories(confForRequest.get("oauth_signature"),
                confForRequest.get("oauth_signature_method"),
                confForRequest.get("oauth_version"),
                confForRequest.get("consumer_key"),
                confForRequest.get("oauth_token"),
                confForRequest.get("oauth_nonce"),
                confForRequest.get("oauth_timestamp"),
                mapForUcozModule.get("page"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
