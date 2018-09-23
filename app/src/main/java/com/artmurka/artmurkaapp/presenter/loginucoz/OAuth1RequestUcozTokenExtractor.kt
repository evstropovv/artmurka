package com.artmurka.artmurkaapp.presenter.loginucoz

import com.github.scribejava.core.model.OAuth1RequestToken

class OAuth1RequestUcozTokenExtractor : AbstractOauth1UcozTokenExtractor<OAuth1RequestToken>() {

    override fun createToken(token: String, secret: String, response: String): OAuth1RequestToken {
        return OAuth1RequestToken(token, secret, response)
    }


}