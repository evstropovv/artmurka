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
        HashMap<String, String> confForRequest = ucoz.get("POST","uapi/shop/wishlisth",mapForUcozModule);

        return ApiModule.getClient().addToWishList(confForRequest);
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Call<WishList> getWishList() {

        UcozApiModule ucoz = new UcozApiModule();
        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();
        mapForUcozModule.put("page", "wishlist");
        HashMap<String, String> confForRequest = ucoz.get("GET","uapi/shop/request",mapForUcozModule);
        return ApiModule.getClient().getWishList(confForRequest);
    }
}
