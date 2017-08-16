package com.artmurka.artmurkaapp.Model.Databases;

import android.content.Context;
import android.content.SharedPreferences;


//Класс для сохранения настроек

public class Preferences {

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
        return preferences.getString("oauth_token", null);
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
        return preferences.getString("oauth_token_secret", null);
    }
}