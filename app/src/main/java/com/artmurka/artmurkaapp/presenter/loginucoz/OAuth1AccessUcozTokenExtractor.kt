package com.artmurka.artmurkaapp.presenter.loginucoz

import com.github.scribejava.core.model.OAuth1AccessToken

class OAuth1AccessUcozTokenExtractor : AbstractOauth1UcozTokenExtractor<OAuth1AccessToken>() {

    override fun createToken(token: String, secret: String, response: String): OAuth1AccessToken {
        return OAuth1AccessToken(token, secret, response)
    }

    private object InstanceHolder {

        private val INSTANCE = OAuth1AccessUcozTokenExtractor()
    }

}