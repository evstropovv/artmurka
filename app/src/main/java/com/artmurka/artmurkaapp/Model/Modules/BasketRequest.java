package com.artmurka.artmurkaapp.Model.Modules;

import com.artmurka.artmurkaapp.Model.InterfacesModel.IBasket;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemBasket.BasketItems;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.SuccessExample;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by Вася on 09.07.2017.
 */

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
       // confForRequest.put("cat_uri", page);

        return ApiModule.getClient().addToBasket(confForRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
