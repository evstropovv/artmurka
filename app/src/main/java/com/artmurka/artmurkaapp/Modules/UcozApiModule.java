package com.artmurka.artmurkaapp.Modules;


import android.util.Base64;
import android.util.Log;

import com.artmurka.artmurkaapp.BuildConfig;
import com.vasyaevstropov.ucozapitest.Retrofit.ApiRetrofit;
import com.vasyaevstropov.ucozapitest.Retrofit.Example;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UcozApiModule {
    private final String url = BuildConfig.URL;
    private final String consumer_key = BuildConfig.CONSUMER_KEY;
    private final String consumer_secret=BuildConfig.CONSUMER_SECRET;
    private final String oauth_token=BuildConfig.OAUTH_TOKEN;
    private final String oauth_token_secret=BuildConfig.OAUTH_TOKEN_SECRET;

    private static Map<String, String> config;
    private static Map<String, String> params;

    String time, oauth_nonce;

    // in PHP -> time();
    //in this method we get current time in SEC, from 1 jan 1970

    public static String getTime() { return String.valueOf(System.currentTimeMillis()/1000);
    }

    //in PHP -> microtime();

    public static String getMicrotime() {
        long mstime = System.currentTimeMillis();
        long right_seconds = mstime / 1000;
        long seconds = mstime / 100000000;
        double decimal = (mstime - (seconds * 100000000)) / 100000000d;
        return decimal +" " + right_seconds;
    }

    private void setConfig(Map<String, String> config) {
        this.config = config;

        time = getTime();
        oauth_nonce = oauth_nonce();

        params = new HashMap<>();
        params.put("oauth_version", "1.0");
        params.put("oauth_timestamp", time);
        params.put("oauth_nonce", oauth_nonce);
        params.put("oauth_signature_method", "HMAC-SHA1");
        params.put("oauth_consumer_key", config.get("oauth_consumer_key"));
        params.put("oauth_token", config.get("oauth_token"));
        params.put("oauth_consumer_secret", config.get("oauth_consumer_secret"));
    }

    //random similar as php -> mt_rand();
    public static String getRandom() {
        int x = (int)(Math.random() * 1234567890) + 75000000;
        return String.valueOf(x);
    }

    //in this method we get ouath_nonce. MD5(Microtime + random)
    private static String oauth_nonce() {
        String st = getMicrotime() + "" + getRandom();
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);
        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }
        return md5Hex;
    }

    /**
     * Создание подписи запроса
     * String method    Метод запроса, например GET
     * string url       URL запроса, например /blog
     * String $params    Все параметры, передаваемые через URL при запросе к API
     * return string
     */

    private String getSignature(String method, String url, String params) {
        String baseString = "";
        try {
            baseString = method + "&" + URLEncoder.encode(url, "UTF-8") + "&" + URLEncoder.encode(params, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.d("Log.d", "Base string: " + baseString);

        String a = "";
        try {
            a = encode(config.get("oauth_consumer_secret") + "&" + config.get("oauth_token_secret"), baseString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public Example get(Map<String, String> config) {
        setConfig(config);

        String signature = getSignature("GET", "http://artmurka.com/uapi/shop/request",
                "oauth_consumer_key=" + params.get("oauth_consumer_key") + "&" +
                        "oauth_nonce=" + params.get("oauth_nonce") + "&" +
                        "oauth_signature_method=" + "HMAC-SHA1" + "&" +
                        "oauth_timestamp=" + params.get("oauth_timestamp") + "&" +
                        "oauth_token=" + params.get("oauth_token") + "&" +
                        "oauth_version=" + "1.0" + "&" +
                        "page=" + "categories");
        Log.d("Log.d", "oauth_signature: " + signature);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://artmurka.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiRetrofit apiRetrofit = retrofit.create(ApiRetrofit.class);

        apiRetrofit.getShopCategories(signature, "HMAC-SHA1", "1.0", "murka", params.get("oauth_token"),params.get("oauth_nonce"),params.get("oauth_timestamp"),"categories")
                .enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.d("Log.d", response.body().getSuccess().get(0).getCatDescr());

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
               Log.d("Log.d","onFailure");
                t.printStackTrace();
            }
        });
        return success;
    }

    public String encode(String key, String data) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA1");
        SecretKeySpec secret = new SecretKeySpec(key.getBytes("UTF-8"), mac.getAlgorithm());
        mac.init(secret);
        byte[] digest = mac.doFinal(data.getBytes());
        String retVal = Base64.encodeToString(digest, Base64.NO_WRAP);
        return URLEncoder.encode(retVal, "UTF-8");
    }
}
