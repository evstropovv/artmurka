package com.artmurka.artmurkaapp.data.model.modules

import android.util.Base64
import android.util.Log

import com.artmurka.artmurkaapp.BuildConfig
import com.artmurka.artmurkaapp.data.model.databases.Preferences

import java.io.UnsupportedEncodingException
import java.math.BigInteger
import java.net.URLEncoder
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.HashMap
import java.util.TreeMap

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class UcozApiModule {

    private val URL = BuildConfig.URL //artmurka

    private var CONSUMER_KEY: String? = null
    private var CONSUMER_SECRET: String? = null
    private var OAUTH_TOKEN: String? = null
    private var OAUTH_TOKEN_SECRET: String? = null

    private val OAUTH_VERSION = BuildConfig.OAUTH_VERSION
    private val OAUTH_SIGNATURE_METHOD = "HMAC-SHA1"

    // in PHP -> time();
    //in this method we get current time in SEC, from 1 jan 1970

    private val time: String
        get() = (System.currentTimeMillis() / 1000).toString()

    //in PHP -> microtime();

    private val microtime: String
        get() {
            val mstime = System.currentTimeMillis()
            val right_seconds = mstime / 1000
            val seconds = mstime / 100000000
            val decimal = (mstime - seconds * 100000000) / 100000000.0
            return decimal.toString() + " " + right_seconds
        }

    //random similar as php -> mt_rand();
    private val random: String
        get() {
            val x = (Math.random() * 1234567890).toInt() + 75000000
            return x.toString()
        }

    //in this method we get ouath_nonce. MD5(Microtime + random)
    private fun oauth_nonce(): String {
        val st = microtime + "" + random
        var messageDigest: MessageDigest? = null
        var digest = ByteArray(0)
        try {
            messageDigest = MessageDigest.getInstance("MD5")
            messageDigest!!.reset()
            messageDigest.update(st.toByteArray())
            digest = messageDigest.digest()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        val bigInt = BigInteger(1, digest)
        var md5Hex = bigInt.toString(16)
        while (md5Hex.length < 32) {
            md5Hex = "0$md5Hex"
        }
        return md5Hex
    }

    /**
     * Создание подписи запроса
     * String method    Метод запроса, например GET
     * string url       URL запроса, например /blog
     * String $params    Все параметры, передаваемые через URL при запросе к API
     * return string
     */

    private fun getSignature(method: String, url: String, params: TreeMap<String, String>): String {
        var baseString = ""
        try {
            val builder = StringBuilder()
            for ((key, value) in params) {
                builder.append(key + value)
                if (key !== params.lastEntry().key) {
                    builder.append("&")
                }
            }
            baseString = method + "&" + URLEncoder.encode(URL + url, "UTF-8") + "&" + URLEncoder.encode(builder.toString(), "UTF-8")
            Log.d("Log.d", "baseString $baseString")

        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        var a = ""
        try {
            a = encode("$CONSUMER_SECRET&$OAUTH_TOKEN_SECRET", baseString)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return a
    }

    operator fun get(method: String, url: String, config: Map<String, String>): HashMap<String, String> {
        //Map<string, string> config -> MUST have:
        // 'method' ("GET"/"PUT")
        // 'url' in format "uapi/shop/request"
        //'id' or 'page'

        CONSUMER_KEY = Preferences.consumerKey
        CONSUMER_SECRET = Preferences.consumerSecret
        OAUTH_TOKEN = Preferences.oauthToken
        OAUTH_TOKEN_SECRET = Preferences.oauthTokenSecret

        val oauth_nonce = oauth_nonce()
        val time = time

        val answerMap = HashMap<String, String>()
        val list = TreeMap<String, String>()

        list["oauth_consumer_key="] = CONSUMER_KEY!!
        list["oauth_nonce="] = oauth_nonce
        list["oauth_signature_method="] = OAUTH_SIGNATURE_METHOD
        list["oauth_timestamp="] = time
        list["oauth_token="] = OAUTH_TOKEN!!
        list["oauth_version="] = OAUTH_VERSION

        for (key in config.keys) {
            list["$key="] = config[key]!!
            answerMap[key] = config[key]!!
        }

        answerMap["oauth_signature"] = getSignature(method, url, list)
        answerMap["oauth_signature_method"] = OAUTH_SIGNATURE_METHOD
        answerMap["oauth_version"] = OAUTH_VERSION
        answerMap["oauth_consumer_key"] = CONSUMER_KEY!!
        answerMap["oauth_token"] = OAUTH_TOKEN!!
        answerMap["oauth_nonce"] = oauth_nonce
        answerMap["oauth_timestamp"] = time

        return answerMap
    }

    @Throws(Exception::class)
    private fun encode(key: String, data: String): String {
        val mac = Mac.getInstance("HmacSHA1")
        val secret = SecretKeySpec(key.toByteArray(charset("UTF-8")), mac.algorithm)
        mac.init(secret)
        val digest = mac.doFinal(data.toByteArray())
        val retVal = Base64.encodeToString(digest, Base64.NO_WRAP)
        return URLEncoder.encode(retVal, "UTF-8")
    }
}