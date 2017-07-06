package com.artmurka.artmurkaapp.Model.Modules;

import android.util.Base64;
import android.util.Log;

import com.artmurka.artmurkaapp.BuildConfig;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class UcozApiModule {

    private final String URL = BuildConfig.URL; //artmurka
    private final String CONSUMER_KEY = BuildConfig.CONSUMER_KEY;
    private final String CONSUMER_SECRET = BuildConfig.CONSUMER_SECRET;
    private final String OAUTH_TOKEN = BuildConfig.OAUTH_TOKEN;
    private final String OAUTH_TOKEN_SECRET = BuildConfig.OAUTH_TOKEN_SECRET;
    private final String OAUTH_VERSION = BuildConfig.OAUTH_VERSION;
    private final String OAUTH_SIGNATURE_METHOD = BuildConfig.OAUTH_SIGNATURE_METHOD;

    // in PHP -> time();
    //in this method we get current time in SEC, from 1 jan 1970

    private static String getTime() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    //in PHP -> microtime();

    private static String getMicrotime() {
        long mstime = System.currentTimeMillis();
        long right_seconds = mstime / 1000;
        long seconds = mstime / 100000000;
        double decimal = (mstime - (seconds * 100000000)) / 100000000d;
        return decimal + " " + right_seconds;
    }

    //random similar as php -> mt_rand();
    public static String getRandom() {
        int x = (int) (Math.random() * 1234567890) + 75000000;
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

    private String getSignature(String method, String url, String params, String page) {
        String baseString = "";
        try {
            baseString = method + "&" + URLEncoder.encode(url, "UTF-8") + (page!=null?"&"+URLEncoder.encode(page+"&","UTF-8"):"&")  + URLEncoder.encode(params, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.d("Log.d", "Base string: " + baseString);

        String a = "";
        try {
            a = encode(CONSUMER_SECRET + "&" + OAUTH_TOKEN_SECRET, baseString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public HashMap<String, String> get(Map<String, String> config) {
        //Map<string, string> config -> MUST have:
        // 'method' ("GET"/"PUT")
        // 'url' in format "uapi/shop/request"
        //'id' or 'page'
        String oauth_nonce = oauth_nonce();
        String time = getTime();

        HashMap<String, String> answerMap = new HashMap<>();
        Log.d("Log.d", "url " +config.get("url"));

        answerMap.put("oauth_signature", getSignature(config.get("method"), URL + config.get("url"),
                "oauth_consumer_key=" + CONSUMER_KEY + "&" +
                        "oauth_nonce=" + oauth_nonce + "&" +
                        "oauth_signature_method=" + OAUTH_SIGNATURE_METHOD + "&" +
                        "oauth_timestamp=" + time + "&" +
                        "oauth_token=" + OAUTH_TOKEN + "&" +
                        "oauth_version=" + OAUTH_VERSION +
                        (config.get("page") != null ? "&" + "page=" + config.get("page") :
                                (config.get("id") != null ? "&" + "id=" + config.get("id") : "")), (config.get("cat_uri")!=null?"cat_uri="+ config.get("cat_uri"):null) ));
        answerMap.put("oauth_signature_method", OAUTH_SIGNATURE_METHOD);
        answerMap.put("oauth_version", OAUTH_VERSION);
        answerMap.put("consumer_key", CONSUMER_KEY);
        answerMap.put("oauth_token", OAUTH_TOKEN);
        answerMap.put("oauth_nonce", oauth_nonce);
        answerMap.put("oauth_timestamp", time);

        if (config.get("page") != null) {
            answerMap.put("page", config.get("page"));
        }
        if (config.get("id") != null) {
            answerMap.put("id", config.get("id"));
        }
        return answerMap;
    }

    private String encode(String key, String data) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA1");
        SecretKeySpec secret = new SecretKeySpec(key.getBytes("UTF-8"), mac.getAlgorithm());
        mac.init(secret);
        byte[] digest = mac.doFinal(data.getBytes());
        String retVal = Base64.encodeToString(digest, Base64.NO_WRAP);
        return URLEncoder.encode(retVal, "UTF-8");
    }
}
