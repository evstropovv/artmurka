package com.artmurka.artmurkaapp.Retrofit;

import java.math.BigInteger;
import java.security.MessageDigest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetroClient {
    private static final String MURKA_URL = "http://artmurka.com/";
    private static final String oauth_version = "1.0";
    private static final String oauth_signature_method = "HMAC-SHA1";

    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(MURKA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static APIService getApiService(){
        return getRetrofitInstance().create(APIService.class);
    }


    public String oauth_nonce() {
        Long time = System.currentTimeMillis();
        String st = time + "." + Math.random();

        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }

    private String oauth_timestamp(){

        return String.valueOf(System.currentTimeMillis()%1000);
    }
}
