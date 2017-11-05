package com.artmurka.artmurkaapp.Model.Modules;

import android.util.Base64;
import android.util.Log;

import com.artmurka.artmurkaapp.BuildConfig;
import com.artmurka.artmurkaapp.Model.Databases.Preferences;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.OrderDesc;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class UcozApiModule {

    private final String URL = BuildConfig.URL; //artmurka

    private String CONSUMER_KEY;
    private String CONSUMER_SECRET;
    private String OAUTH_TOKEN;
    private String OAUTH_TOKEN_SECRET;

    private final String OAUTH_VERSION = BuildConfig.OAUTH_VERSION;
    private final String OAUTH_SIGNATURE_METHOD = "HMAC-SHA1";

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
    private static String getRandom() {
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

    private String getSignature(String method, String url, TreeMap<String, String> params) {
        String baseString = "";
        try {
            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.append(entry.getKey() + URLEncoder.encode( entry.getValue(), "UTF-8")); //URLEncoder.encode.. <-- для подгруп.
                if (entry.getKey() != params.lastEntry().getKey()){
                    builder.append("&");
                }
            }
            baseString = method + "&" + URLEncoder.encode(URL + url, "UTF-8") + "&" + URLEncoder.encode(builder.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String a = "";
        try {
            a = encode(CONSUMER_SECRET + "&" + OAUTH_TOKEN_SECRET, baseString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public HashMap<String, String> get(String method, String url, Map<String, String> config) {
        //Map<string, string> config -> MUST have:
        // 'method' ("GET"/"PUT")
        // 'url' in format "uapi/shop/request"
        //'id' or 'page'

        CONSUMER_KEY = Preferences.getConsumerKey();
        CONSUMER_SECRET = Preferences.getConsumerSecret();
        OAUTH_TOKEN = Preferences.getOauthToken();
        OAUTH_TOKEN_SECRET = Preferences.getOauthTokenSecret();

        String oauth_nonce = oauth_nonce();
        String time = getTime();

        HashMap<String, String> answerMap = new HashMap<>();
        TreeMap<String,String> list = new TreeMap<>();

        list.put("oauth_consumer_key=",CONSUMER_KEY);
        list.put("oauth_nonce=",oauth_nonce);
        list.put("oauth_signature_method=",OAUTH_SIGNATURE_METHOD);
        list.put("oauth_timestamp=",time);
        list.put("oauth_token=",OAUTH_TOKEN);
        list.put("oauth_version=",OAUTH_VERSION);

        for (String key : config.keySet()) {
            list.put(key+"=", config.get(key));
            answerMap.put(key, config.get(key));
        }

        answerMap.put("oauth_signature", getSignature(method, url, list));
        answerMap.put("oauth_signature_method", OAUTH_SIGNATURE_METHOD);
        answerMap.put("oauth_version", OAUTH_VERSION);
        answerMap.put("oauth_consumer_key", CONSUMER_KEY);
        answerMap.put("oauth_token", OAUTH_TOKEN);
        answerMap.put("oauth_nonce", oauth_nonce);
        answerMap.put("oauth_timestamp", time);

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
