package com.artmurka.artmurkaapp.Model.Modules;

import com.artmurka.artmurkaapp.Model.InterfacesModel.IAboutGoods;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods.AboutGood;

import java.util.HashMap;

import retrofit2.Call;

public class AboutGoodsRequest implements IAboutGoods {

    @Override
    public Call<AboutGood> getDataAboutGood(String id) {
        UcozApiModule ucoz = new UcozApiModule();

        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();
        mapForUcozModule.put("page", "viewgoods");
        mapForUcozModule.put("method", "GET");
        mapForUcozModule.put("url", "uapi/shop/request");
        mapForUcozModule.put("id", id);

        //Getting all token for autorization.
        HashMap<String, String> confForRequest = ucoz.get(mapForUcozModule);
        confForRequest.put("page", mapForUcozModule.get("page"));

        return ApiModule.getClient().getGoodDescription(confForRequest);
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread());
    }
}
