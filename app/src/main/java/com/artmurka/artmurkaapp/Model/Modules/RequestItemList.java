package com.artmurka.artmurkaapp.Model.Modules;

import android.util.Log;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.SuccessExample;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RequestItemList implements IRequestItemList {

    @Override
    public Observable<SuccessExample> getItemList(String page, String pageNumber) {
        UcozApiModule ucoz = new UcozApiModule();

        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();
        mapForUcozModule.put("cat_uri", page);
        mapForUcozModule.put("method", "GET");
        mapForUcozModule.put("url", "uapi/shop/cat");
        mapForUcozModule.put("pnum", pageNumber);

        HashMap<String, String> confForRequest = ucoz.get(mapForUcozModule);

        confForRequest.put("cat_uri", page);
        Log.d("Log.d", "confreqyest map " + confForRequest.toString());

        Log.d("Log.d", "url must be : " + "http://artmurka.com/uapi/shop/cat?" +
                "oauth_signature=" + confForRequest.get("oauth_signature") +
                "&oauth_nonce=" + confForRequest.get("oauth_nonce") +
                "&oauth_version=" + confForRequest.get("oauth_version") +
                "&oauth_consumer_key=" + confForRequest.get("oauth_consumer_key") +
                "&oauth_signature_method=" + confForRequest.get("oauth_signature_method") +
                "&oauth_token=" + confForRequest.get("oauth_token") +
                "&oauth_timestamp=" + confForRequest.get("oauth_timestamp") +
                "&cat_uri=" + confForRequest.get("cat_uri")
        );

        return ApiModule.getClient().getItemList(confForRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
