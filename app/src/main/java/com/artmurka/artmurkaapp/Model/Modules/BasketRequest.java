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
        mapForUcozModule.put("id", goodId);
        mapForUcozModule.put("mode", "add");

        HashMap<String, String> confForRequest = ucoz.get("POST", "uapi/shop/basket", mapForUcozModule);

        return ApiModule.getClient().addToBasket(confForRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BasketItems> getItemInBasket() {
        UcozApiModule ucoz = new UcozApiModule();

        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();
        HashMap<String, String> confForRequest = ucoz.get("GET","uapi/shop/basket/", mapForUcozModule);

        return ApiModule.getClient().getGoodsInBasket(confForRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Call<BasketItems> deleteItemFromBasket(String goodId) {
        UcozApiModule ucoz = new UcozApiModule();

        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();
        mapForUcozModule.put("goodId", goodId);
        HashMap<String, String> confForRequest = ucoz.get("DELETE","uapi/shop/basket/",mapForUcozModule);

        return ApiModule.getClient().deleteItemInBasket(confForRequest);
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread());

    }
}
