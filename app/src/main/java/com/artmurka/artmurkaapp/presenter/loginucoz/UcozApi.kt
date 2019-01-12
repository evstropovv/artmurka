package com.artmurka.artmurkaapp.presenter.loginucoz

import com.github.scribejava.core.builder.api.DefaultApi10a
import com.github.scribejava.core.extractors.TokenExtractor
import com.github.scribejava.core.model.OAuth1AccessToken
import com.github.scribejava.core.model.OAuth1RequestToken

class UcozApi: DefaultApi10a() {
    override fun getAuthorizationBaseUrl(): String {
       return AUTHORIZE_URL
    }

    override fun getAccessTokenEndpoint(): String {
        return "http://uapi.ucoz.com/accounts/oauthgetaccesstoken"
    }

    override fun getRequestTokenEndpoint(): String {
        return "http://uapi.ucoz.com/accounts/oauthgetrequesttoken"
    }

    override fun getAuthorizationUrl(requestToken: OAuth1RequestToken): String {
        return String.format(AUTHORIZE_URL, requestToken.token)
    }

    override fun getAccessTokenExtractor(): TokenExtractor<OAuth1AccessToken> {
        return OAuth1AccessUcozTokenExtractor()
    }

    override fun getRequestTokenExtractor(): TokenExtractor<OAuth1RequestToken> {
        return OAuth1RequestUcozTokenExtractor()
    }

    private val AUTHORIZE_URL = "http://uapi.ucoz.com/accounts/oauthauthorizetoken?oauth_token=%s"

}