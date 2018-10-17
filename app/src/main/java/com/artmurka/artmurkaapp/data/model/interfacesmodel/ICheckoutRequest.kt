package com.artmurka.artmurkaapp.data.model.interfacesmodel

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.CheckoutAllGoods
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkoutresponse.CheckoutResponse

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call

interface ICheckoutRequest {
    val checkoutData: Observable<CheckoutAllGoods>
    fun recountCheckoutData(position: String, cnt: String): Single<CheckoutAllGoods>
    fun postCheckout(telephoneNumber: String, adress: String, email: String, pay: String, delivery: String): Call<CheckoutResponse>
}
