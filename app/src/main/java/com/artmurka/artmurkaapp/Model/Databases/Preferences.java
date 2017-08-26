package com.artmurka.artmurkaapp.Model.Databases;

import android.content.Context;
import android.content.SharedPreferences;

import com.artmurka.artmurkaapp.BuildConfig;


//Класс для хранения токенов()

public class Preferences {

    private static final String CONSUMER_KEY = BuildConfig.CONSUMER_KEY;
    private static final String CONSUMER_SECRET = BuildConfig.CONSUMER_SECRET;
    private static final String OAUTH_TOKEN = BuildConfig.OAUTH_TOKEN;
    private static final String OAUTH_TOKEN_SECRET = BuildConfig.OAUTH_TOKEN_SECRET;


    public static final String STORAGE_NAME = "applicationTheme";
    private static SharedPreferences preferences = null;
    private static SharedPreferences.Editor editor = null;
    private static Context context = null;

    public static void init(Context cntxt) {
        context = cntxt;
    }

    private static void init() {
        preferences = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }
    public static void setConsumerKey(String consumerKey){
        if (preferences == null) {
            init();
        }
        editor.putString("consumer_key", consumerKey);
        editor.commit();
    }

    public static String getConsumerKey(){
        if (preferences ==null){
            init();
        }
        return preferences.getString("consumer_key", CONSUMER_KEY);
    }

    public static void setConsumerSecret(String consumerSecret){
        if (preferences == null) {
            init();
        }
        editor.putString("consumer_secret", consumerSecret);
        editor.commit();
    }

    public static String getConsumerSecret(){
        if (preferences ==null){
            init();
        }
        return preferences.getString("consumer_secret", CONSUMER_SECRET);
    }

    public static void setOauthToken(String oauthToken){
        if (preferences == null) {
            init();
        }
        editor.putString("oauth_token", oauthToken);
        editor.commit();
    }

    public static String getOauthToken(){
        if (preferences ==null){
            init();
        }
        return preferences.getString("oauth_token", OAUTH_TOKEN);
    }

    public static void setOauthTokenSecret(String oauthTokenSecret){
        if (preferences == null) {
            init();
        }
        editor.putString("oauth_token_secret", oauthTokenSecret);
        editor.commit();
    }

    public static String getOauthTokenSecret(){
        if (preferences ==null){
            init();
        }
        return preferences.getString("oauth_token_secret", OAUTH_TOKEN_SECRET);
    }
}