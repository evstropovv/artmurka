package com.artmurka.artmurkaapp.Model.Modules;

import com.artmurka.artmurkaapp.Model.InterfacesModel.IAllRequestObservers;
import com.artmurka.artmurkaapp.Model.Retrofit.Example;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class AllRequestOvservers implements IAllRequestObservers {

    @Override
    public Observable<Example> getCategories() {
        UcozApiModule ucoz = new UcozApiModule();
        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();
        mapForUcozModule.put("page", "categories");
        HashMap<String, String> confForRequest = ucoz.get("GET","uapi/shop/request" ,mapForUcozModule);

        return ApiModule.getClient().getShopCategories(confForRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
