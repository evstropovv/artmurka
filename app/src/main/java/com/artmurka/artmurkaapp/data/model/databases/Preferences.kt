package com.artmurka.artmurkaapp.data.model.databases

import android.content.Context
import android.content.SharedPreferences

import com.artmurka.artmurkaapp.Constants
import com.artmurka.artmurkaapp.R
import java.lang.ref.WeakReference


//Класс для хранения токенов()

object Preferences {

    private val CONSUMER_KEY = Constants.consumerkey()
    private val CONSUMER_SECRET = Constants.consumersecret()
    private val OAUTH_TOKEN = Constants.oauthtoken()
    private val OAUTH_TOKEN_SECRET = Constants.oauthtokensecret()


    val STORAGE_NAME = "applicationTheme"
    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private var context: WeakReference<Context>? = null

    var consumerKey: String
        get() {
            if (preferences == null) {
                init()
            }
            return preferences!!.getString("consumer_key", CONSUMER_KEY)
        }
        set(consumerKey) {
            if (preferences == null) {
                init()
            }
            editor!!.putString("consumer_key", consumerKey)
            editor!!.commit()
        }

    var consumerSecret: String
        get() {
            if (preferences == null) {
                init()
            }
            return preferences!!.getString("consumer_secret", CONSUMER_SECRET)
        }
        set(consumerSecret) {
            if (preferences == null) {
                init()
            }
            editor!!.putString("consumer_secret", consumerSecret)
            editor!!.commit()
        }

    var oauthToken: String
        get() {
            if (preferences == null) {
                init()
            }
            return preferences!!.getString("oauth_token", OAUTH_TOKEN)
        }
        set(oauthToken) {
            if (preferences == null) {
                init()
            }
            editor!!.putString("oauth_token", oauthToken)
            editor!!.commit()
        }

    var oauthTokenSecret: String
        get() {
            if (preferences == null) {
                init()
            }
            return preferences!!.getString("oauth_token_secret", OAUTH_TOKEN_SECRET)
        }
        set(oauthTokenSecret) {
            if (preferences == null) {
                init()
            }
            editor!!.putString("oauth_token_secret", oauthTokenSecret)
            editor!!.commit()
        }

    var name: String
        get() {
            if (preferences == null) {
                init()
            }
            return preferences!!.getString("name", "Арт-Мурка")
        }
        set(name) {
            if (preferences == null) {
                init()
            }
            editor!!.putString("name", name)
            editor!!.commit()
        }

    var email: String
        get() {
            if (preferences == null) {
                init()
            }
            return preferences!!.getString("email", "artmurka.com")
        }
        set(email) {
            if (preferences == null) {
                init()
            }
            editor!!.putString("email", email)
            editor!!.commit()
        }

    var isLogin: Boolean?
        get() {
            if (preferences == null) {
                init()
            }
            return preferences!!.getBoolean("isLogin", false)
        }
        set(isLogin) {
            if (preferences == null) {
                init()
            }
            editor!!.putBoolean("isLogin", isLogin!!)
            editor!!.commit()
        }

    var listSettings: Int?
        get() {
            if (preferences == null) {
                init()
            }
            return preferences!!.getInt("list", 2)
        }
        set(list) {
            if (preferences == null) {
                init()
            }
            editor!!.putInt("list", list!!)
            editor!!.commit()
        }

    var listUrl: String
        get() {
            if (preferences == null) {
                init()
            }
            return preferences!!.getString("listurl", "vushivki")
        }
        set(url) {
            if (preferences == null) {
                init()
            }
            editor!!.putString("listurl", url)
            editor!!.commit()
        }

    var checkedRadioButton: Int?
        get() {
            if (preferences == null) {
                init()
            }
            return preferences!!.getInt("radiobutton", R.id.rbName)
        }
        set(rb) {
            if (preferences == null) {
                init()
            }
            editor!!.putInt("radiobutton", rb!!)
            editor!!.commit()
        }

    fun init(cntxt: Context) {
        context = WeakReference<Context>(cntxt)
    }

    private fun init() {
        preferences = context?.get()!!.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
        editor = preferences!!.edit()
    }


}