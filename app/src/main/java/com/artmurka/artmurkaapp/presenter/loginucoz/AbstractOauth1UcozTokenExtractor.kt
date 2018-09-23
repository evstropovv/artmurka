package com.artmurka.artmurkaapp.presenter.loginucoz

import com.github.scribejava.core.exceptions.OAuthException
import com.github.scribejava.core.extractors.TokenExtractor
import com.github.scribejava.core.model.OAuth1Token
import com.github.scribejava.core.model.Response
import com.github.scribejava.core.utils.OAuthEncoder
import com.github.scribejava.core.utils.Preconditions

import java.io.IOException
import java.util.regex.Matcher
import java.util.regex.Pattern


abstract class AbstractOauth1UcozTokenExtractor<T : OAuth1Token> : TokenExtractor<T> {

    private val OAUTH_TOKEN_PATTERN = Pattern.compile("\"oauth_token\"\\s*:\\s*\"(\\S*?)\"")
    private val OAUTH_TOKEN_SECRET_PATTERN = Pattern.compile("\"oauth_token_secret\"\\s*:\\s*\"(\\S*?)\"")

    @Throws(IOException::class)
    override fun extract(response: Response): T {
        val body = response.body
        Preconditions.checkEmptyString(body,
                "Response body is incorrect. " + "Can't extract a token from an empty string")
        val token = extract(body, OAUTH_TOKEN_PATTERN)
        val secret = extract(body, OAUTH_TOKEN_SECRET_PATTERN)
        return createToken(token, secret, body)
    }

    private fun extract(response: String, p: Pattern): String {
        val matcher = p.matcher(response)
        return if (matcher.find() && matcher.groupCount() >= 1) {
            OAuthEncoder.decode(matcher.group(1))
        } else {
            throw OAuthException("Response body is incorrect. Can't extract token and secret from this: '"
                    + response + "'", null)
        }
    }

    protected abstract fun createToken(token: String, secret: String, response: String): T
}
