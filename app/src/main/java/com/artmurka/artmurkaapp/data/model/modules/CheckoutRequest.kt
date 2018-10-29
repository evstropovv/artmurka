package com.artmurka.artmurkaapp.data.model.modules

import android.util.Log

import com.artmurka.artmurkaapp.domain.interfacesmodel.ICheckoutRequest
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.CheckoutAllGoods
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkoutresponse.CheckoutResponse
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit
import com.google.gson.Gson

import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.HashMap
import java.util.TreeMap

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import javax.inject.Inject

class CheckoutRequest @Inject constructor(val apiModule: ApiRetrofit, val ucoz : UcozApiModule ) : ICheckoutRequest {

    override val checkoutData: Observable<CheckoutAllGoods>
        get() {

            val mapForUcozModule = HashMap<String, String>()
            val confForRequest = ucoz.get("GET", "uapi/shop/checkout/", mapForUcozModule)

            return apiModule.getCheckout(confForRequest)
        }

    override fun recountCheckoutData(position: String, id: String): Single<CheckoutAllGoods> {

        val mapForUcozModule = HashMap<String, String>()
        mapForUcozModule["mode"] = "recalc"
        mapForUcozModule["cnt_$position"] = id
        val confForRequest = ucoz.get("PUT", "uapi/shop/checkout/", mapForUcozModule)
        return apiModule.recountCheckoutData(confForRequest)
    }


    override fun postCheckout(telephone: String, msg: String, email: String, pay: String, delivery: String): Single<CheckoutResponse> {

        val mapForUcozModule = TreeMap<String, String>()
        mapForUcozModule["mode"] = "order"
        mapForUcozModule["payment_id"] = pay //1 -
        mapForUcozModule["delivery_id"] = delivery
        mapForUcozModule["fld1"] = telephone

        var encodeEmail: String? = null
        var encodeMsg: String? = null
        val msgTrim = msg.replace(" ", "") //убираем пробелы
        try {
            encodeEmail = URLEncoder.encode(email, "UTF-8")
            encodeMsg = URLEncoder.encode(msgTrim, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        mapForUcozModule["fld2"] = encodeMsg!!
        mapForUcozModule["fld3"] = encodeEmail!!

        val confForRequest2 = ucoz.get("POST", "uapi/shop/checkout/", mapForUcozModule)

        val reqBodyMap = HashMap<String, String>()
        reqBodyMap["oauth_signature"] = confForRequest2["oauth_signature"]!!
        reqBodyMap["oauth_signature_method"] = confForRequest2["oauth_signature_method"]!!
        reqBodyMap["oauth_version"] = confForRequest2["oauth_version"]!!
        reqBodyMap["oauth_consumer_key"] = confForRequest2["oauth_consumer_key"]!!
        reqBodyMap["oauth_token"] = confForRequest2["oauth_token"]!!
        reqBodyMap["oauth_nonce"] = confForRequest2["oauth_nonce"]!!
        reqBodyMap["oauth_timestamp"] = confForRequest2["oauth_timestamp"]!!

        reqBodyMap["mode"] = "order"
        reqBodyMap["payment_id"] = pay
        reqBodyMap["delivery_id"] = delivery

        reqBodyMap["fld1"] = telephone
        reqBodyMap["fld2"] = encodeMsg!!
        reqBodyMap["fld3"] = encodeEmail!!

        return apiModule.postCheckout(confForRequest2)
    }
}