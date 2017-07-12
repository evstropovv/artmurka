package com.artmurka.artmurkaapp.Model.Modules;

import com.artmurka.artmurkaapp.Model.InterfacesModel.IBasket;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemBasket.BasketItems;


import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;


public class BasketRequest implements IBasket {
    @Override
    public Observable<BasketItems> toBasket(String goodId) {
        UcozApiModule ucoz = new UcozApiModule();

        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();

        mapForUcozModule.put("goodId", goodId);
        mapForUcozModule.put("method", "GET");
        mapForUcozModule.put("mode", "add");
        mapForUcozModule.put("url", "uapi/shop/basket/");

        HashMap<String, String> confForRequest = ucoz.get(mapForUcozModule);

        return ApiModule.getClient().addToBasket(confForRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BasketItems> getItemInBasket() {
        UcozApiModule ucoz = new UcozApiModule();

        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();
        mapForUcozModule.put("method", "GET");
        mapForUcozModule.put("url", "uapi/shop/basket/");

        HashMap<String, String> confForRequest = ucoz.get(mapForUcozModule);

        return ApiModule.getClient().addToBasket(confForRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Call<BasketItems> deleteItemFromBasket(String goodId) {
        UcozApiModule ucoz = new UcozApiModule();

        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();
        mapForUcozModule.put("method", "DELETE");
        mapForUcozModule.put("url", "uapi/shop/basket/");
        mapForUcozModule.put("goodId", goodId);

        HashMap<String, String> confForRequest = ucoz.get(mapForUcozModule);

        return ApiModule.getClient().deleteItemInBasket(confForRequest);
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread());

    }
}
