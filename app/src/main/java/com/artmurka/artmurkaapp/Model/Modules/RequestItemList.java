package com.artmurka.artmurkaapp.Model.Modules;

import android.util.Log;

import com.artmurka.artmurkaapp.Model.InterfacesModel.IRequestItemList;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemList.SuccessExample;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RequestItemList implements IRequestItemList {

    @Override
    public Observable<SuccessExample> getItemList(String page, String pageNumber, String sort, String order) {

        UcozApiModule ucoz = new UcozApiModule();

        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();

        String encodePage = null;
        try {
            encodePage = URLEncoder.encode(page, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        mapForUcozModule.put("cat_uri", encodePage);
        mapForUcozModule.put("pnum", pageNumber);
        mapForUcozModule.put("sort", sort);
        mapForUcozModule.put("order", order);


        HashMap<String, String> confForRequest = ucoz.get("GET","uapi/shop/cat", mapForUcozModule);
        confForRequest.put("cat_uri", page);

        return ApiModule.getClient().getItemList(confForRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
