package com.artmurka.artmurkaapp.domain.usecase.checkout

import com.artmurka.artmurkaapp.data.model.modules.UcozApiModule
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkoutresponse.CheckoutResponse
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit
import com.artmurka.artmurkaapp.domain.usecase.base.AbsUseCaseSingle
import io.reactivex.Single
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.*
import javax.inject.Inject

class PostCheckoutUseCase @Inject constructor(val apiModule: ApiRetrofit, val ucoz: UcozApiModule): AbsUseCaseSingle<CheckoutResponse, PostCheckoutUseCase.Params>() {

    override fun buildUseCaseObservable(params: Params): Single<CheckoutResponse> {

        val mapForUcozModule = TreeMap<String, String>()
        mapForUcozModule["mode"] = "order"
        mapForUcozModule["payment_id"] = params.pay //1 -
        mapForUcozModule["delivery_id"] = params.delivery
        mapForUcozModule["fld1"] = params.telephone

        var encodeEmail: String? = null
        var encodeMsg: String? = null
        val msgTrim = params.msg.replace(" ", "") //убираем пробелы
        try {
            encodeEmail = URLEncoder.encode(params.email, "UTF-8")
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
        reqBodyMap["payment_id"] = params.pay
        reqBodyMap["delivery_id"] = params.delivery

        reqBodyMap["fld1"] = params.telephone
        reqBodyMap["fld2"] = encodeMsg
        reqBodyMap["fld3"] = encodeEmail
        return apiModule.postCheckout(confForRequest2)
    }

    class Params(val telephone: String,val msg: String,val email: String,val pay: String, val delivery: String)
}