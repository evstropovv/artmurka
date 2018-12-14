package com.artmurka.artmurkaapp.android.views.activities.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.CookieManager
import android.webkit.CookieSyncManager
import android.webkit.WebView
import android.webkit.WebViewClient
import com.artmurka.artmurkaapp.Constants
import com.artmurka.artmurkaapp.data.model.databases.Preferences
import com.artmurka.artmurkaapp.presenter.loginucoz.UcozApi
import com.artmurka.artmurkaapp.presenter.loginucoz.User
import com.artmurka.artmurkaapp.R
import com.github.scribejava.core.builder.ServiceBuilder
import com.github.scribejava.core.model.OAuth1AccessToken
import com.github.scribejava.core.model.OAuth1RequestToken
import com.github.scribejava.core.model.OAuthRequest
import com.github.scribejava.core.model.Response
import com.github.scribejava.core.model.Verb
import com.github.scribejava.core.oauth.OAuth10aService
import com.google.gson.GsonBuilder

import java.io.IOException
import java.util.concurrent.ExecutionException

class LoginActivity : AppCompatActivity() {
    private var webView: WebView? = null
    private var verifier: String? = null
    private var requestToken: OAuth1RequestToken? = null
    private var service: OAuth10aService? = null
    private var accessToken: OAuth1AccessToken? = null
    private var user: User? = null

    @SuppressLint("StaticFieldLeak")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        webView = findViewById<WebView>(R.id.webView)
        webView!!.clearCache(true)
        webView!!.settings.javaScriptEnabled = true
        webView!!.settings.userAgentString = "Mozilla/5.0 (X11; Linux x86_64; rv:10.0) Gecko/20100101 Firefox/10.0"
        webView!!.settings.builtInZoomControls = true
        webView!!.settings.displayZoomControls = false
        service = ServiceBuilder(Constants.loginconsumerkey())
                .apiSecret(Constants.loginconsumersecret())
                .debug()
                .build(UcozApi)

        object : AsyncTask<Void, Void, String>() {
            override fun doInBackground(vararg params: Void): String {
                try {
                    requestToken = service!!.requestToken
                } catch (e: IOException) {
                    e.printStackTrace()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } catch (e: ExecutionException) {
                    e.printStackTrace()
                }

                return service!!.getAuthorizationUrl(requestToken)
            }

            override fun onPostExecute(result: String) {
                loadURL(result)
            }
        }.execute()


    }

    fun loadURL(url: String) {
        deleteCokies()

        webView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                val uri = Uri.parse(url)
                if (url.contains("oauth_verifier")) {
                    webView!!.visibility = View.GONE
                    verifier = uri.getQueryParameter("oauth_verifier")
                    getAccessToken()
                }
                return false
            }
        }
        webView!!.loadUrl(url)
    }

    private fun deleteCokies() {
        val cookieManager = CookieManager.getInstance()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(this)
            cookieManager.removeAllCookie()
        } else {
            cookieManager.setAcceptCookie(true)
            cookieManager.removeAllCookies { }
        }
    }

    private fun getAccessToken() {
        object : AsyncTask<Void, Void, OAuth1AccessToken>() {
            override fun doInBackground(vararg params: Void): OAuth1AccessToken? {
                try {
                    accessToken = service!!.getAccessToken(requestToken, verifier)
                } catch (e: IOException) {
                    e.printStackTrace()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } catch (e: ExecutionException) {
                    e.printStackTrace()
                }

                //get user info
                val request = OAuthRequest(Verb.GET, "http://uapi.ucoz.com/accounts/GetUserInfo")
                service!!.signRequest(accessToken!!, request)
                var response: Response? = null
                try {
                    response = service!!.execute(request)
                    val builder = GsonBuilder()
                    val gson = builder.create()
                    user = gson.fromJson(response!!.body, User::class.java)

                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } catch (e: ExecutionException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                return accessToken
            }

            override fun onPostExecute(result: OAuth1AccessToken) {
                val intent = Intent()
                if (result.token != null) {
                    Preferences.consumerKey = Constants.loginconsumerkey()
                    Preferences.consumerSecret = Constants.loginconsumersecret()
                    Preferences.oauthToken = result.token
                    Preferences.oauthTokenSecret = result.tokenSecret
                    Preferences.name = user!!.nickname!!
                    Preferences.email = user!!.email!!
                    intent.putExtra("name", user!!.nickname)
                    intent.putExtra("email", user!!.email)

                } else {

                    intent.putExtra("name", "Арт-Мурка")
                    intent.putExtra("email", "artmurka.com")
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }.execute()
    }

    override fun onBackPressed() {
        if (webView!!.canGoBack()) {
            webView!!.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
