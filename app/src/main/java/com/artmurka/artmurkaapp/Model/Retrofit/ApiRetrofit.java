package com.artmurka.artmurkaapp.Model.Retrofit;


import com.artmurka.artmurkaapp.Model.Pojo.ItemList.*;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Success;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiRetrofit {
    
    @GET("uapi/shop/request")
    Observable<Example> getShopCategories(@QueryMap HashMap<String, String> map);


    //oauth_signature oauth_signature_method oauth_version oauth_consumer_key oauth_token oauth_nonce oauth_timestamp page
    @GET("uapi/shop/cat")
    Call<Success> getItemList(@QueryMap HashMap<String, String> map);

}
