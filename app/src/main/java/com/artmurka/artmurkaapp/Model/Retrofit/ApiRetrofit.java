package com.artmurka.artmurkaapp.Model.Retrofit;


import com.artmurka.artmurkaapp.Model.Pojo.ItemList.*;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiRetrofit {

    @GET("uapi/shop/request")
    Observable<Example> getShopCategories(@QueryMap HashMap<String, String> map);

    //oauth_signature oauth_signature_method oauth_version oauth_consumer_key oauth_token oauth_nonce oauth_timestamp page
    @GET("uapi/shop/cat")
    Observable<SuccessExample> getItemList(@QueryMap HashMap<String, String> map);

    //add to item to BASKET
    @POST("uapi/shop/basket/")
    Observable<ResponseBody> addToBasket(@QueryMap HashMap<String, String> map);
}
