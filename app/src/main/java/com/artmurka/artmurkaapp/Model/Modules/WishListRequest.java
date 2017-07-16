package com.artmurka.artmurkaapp.Model.Modules;

import com.artmurka.artmurkaapp.Model.InterfacesModel.IWishList;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.WishList.WishList;

import java.util.HashMap;

import retrofit2.Call;


public class WishListRequest implements IWishList {

    @Override
    public Call<WishList> toWishList(String goods_id) {
        UcozApiModule ucoz = new UcozApiModule();

        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();
        mapForUcozModule.put("goods_id", goods_id);
        mapForUcozModule.put("method", "POST");
        mapForUcozModule.put("url", "uapi/shop/wishlisth");

        HashMap<String, String> confForRequest = ucoz.get(mapForUcozModule);

        return ApiModule.getClient().addToWishList(confForRequest);
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread());
    }
}
