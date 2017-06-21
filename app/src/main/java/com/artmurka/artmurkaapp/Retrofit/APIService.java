package com.artmurka.artmurkaapp.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface APIService {

//    curl -i -X GET -G -H 'Authorization: OAuth
//    oauth_consumer_key="murka",
//    oauth_nonce="c01938894e018a5fe8bf77710726195",
//    oauth_signature="4fx4%2BE%2FAQ3ouHzOpLnMLblZ0Eus%3D",
//    oauth_signature_method="HMAC-SHA1",
//    oauth_timestamp="1497992371",
//    oauth_token="paJTN0ZA6KJGAWgHDRKPVNgFBOOe.qMOl8x5pY2W"'
//            --data-urlencode "page=allgoods" http://artmurka.com/uapi/shop/request?page=allgoods


    @GET("uapi/shop/request?")
    Call<List<OneCategory>> getShopList(@Query("oauth_consumer_key") String oauth_consumer_key,
                                        @Query("oauth_nonce") String oauth_nonce,
                                        @Query("oauth_signature_method") String oauth_signature_method,
                                        @Query("oauth_timestamp") String oauth_timestamp,
                                        @Query("oauth_version") String oauth_version

//                                    ,@Query("oauth_consumer_secret") String oauth_consumer_secret,
//                                    @Query("oauth_token") String oauth_token,
//                                    @Query("oauth_token_secret") String oauth_token_secret
    );


    @POST("accounts/OAuthGetRequestToken?") // ? - убрать?
    Call<OAuthGetRequestToken> getOAuthGetRequestToken (
            @Query("oauth_consumer_key")String oauth_consumer_key,
            @Query("oauth_signature_method")String oauth_signature_method,
            @Query("oauth_signature")String oauth_signature,
            @Query("oauth_timestamp")String oauth_timestamp,
            @Query("oauth_nonce")String oauth_nonce,
            @Query("oauth_callback")String oauth_callback);

}
