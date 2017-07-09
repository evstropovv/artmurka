package com.artmurka.artmurkaapp.Model.Modules;

import com.artmurka.artmurkaapp.Model.InterfacesModel.IRequestItemList;
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

        return ApiModule.getClient().getItemList(confForRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}