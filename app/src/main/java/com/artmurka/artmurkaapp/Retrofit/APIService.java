package com.artmurka.artmurkaapp.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface APIService {

//    curl -i -X GET -G
//    --data-urlencode "oauth_consumer_key=ЗНАЧЕНИЕ"
//            --data-urlencode "oauth_nonce=значение"
//            --data-urlencode "oauth_signature_method=HMAC-SHA1"
//            --data-urlencode "oauth_timestamp=значение"
//            --data-urlencode "oauth_version=1.0"
//            --data-urlencode "oauth_token=значение"
//            --data-urlencode "auth_signature=значение"



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
}
