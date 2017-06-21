package com.artmurka.artmurkaapp.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OAuthGetRequestToken {

        @SerializedName("oauth_token ")
        @Expose
        private String oauth_token ;
        @SerializedName("oauth_token_secret")
        @Expose
        private String oauth_token_secret;


    public String getOauth_token () {
            return oauth_token ;
        }

    public String getOauth_token_secret() {
            return oauth_token_secret;
        }

}
