package com.artmurka.artmurkaapp.Model.Retrofit;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRetrofit {

    @GET("uapi/shop/request")
    Observable<Example> getShopCategories(@Query("oauth_signature") String oauth_signature,
                                          @Query("oauth_signature_method") String oauth_signature_method,
                                          @Query("oauth_version") String oauth_version,
                                          @Query("oauth_consumer_key") String oauth_consumer_key,
                                          @Query("oauth_token") String oauth_token,
                                          @Query("oauth_nonce") String oauth_nonce,
                                          @Query("oauth_timestamp") String oauth_timestamp,
                                          @Query("page") String page);
}
