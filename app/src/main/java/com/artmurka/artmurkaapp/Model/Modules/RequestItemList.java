package com.artmurka.artmurkaapp.Model.Modules;

import android.util.Log;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Success;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RequestItemList implements IRequestItemList {

    @Override
    public Observable <Success> getItemList(String page) {
        UcozApiModule ucoz = new UcozApiModule();

        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();
        mapForUcozModule.put("cat_uri", page);
        mapForUcozModule.put("method", "GET");
        mapForUcozModule.put("url", "uapi/shop/cat");

        HashMap<String, String> confForRequest = ucoz.get(mapForUcozModule);
        Log.d("Log.d", "confreqyest map " + confForRequest.toString());

        return ApiModule.getClient().getItemList(confForRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
