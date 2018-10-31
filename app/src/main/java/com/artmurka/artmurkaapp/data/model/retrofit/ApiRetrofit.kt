package com.artmurka.artmurkaapp.data.model.retrofit


import com.artmurka.artmurkaapp.data.model.pojo.itemlist.aboutgoods.AboutGood
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.*
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.CheckoutAllGoods
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkoutresponse.CheckoutResponse
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.good.Good
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.BasketItems
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.SuccessExample
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.Areas.AreasRequest
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.Areas.AreasResponse
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.CityRequest.City
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.CityResponse.CityResponse
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.WarehousesRequest.WarehouseRequest
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.WarehousesResponse.WarehouseResponse
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.orders.Orders

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.WishList

import java.util.HashMap

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.QueryMap

interface ApiRetrofit {

    @GET("uapi/shop/request")
    fun getShopCategories(@QueryMap map: HashMap<String, String>): Observable<Example>

    //oauth_signature oauth_signature_method oauth_version oauth_consumer_key oauth_token oauth_nonce oauth_timestamp page
    @GET("uapi/shop/cat")
    fun getItemList(@QueryMap map: HashMap<String, String>): Observable<SuccessExample>

    //add to item to BASKET
    @FormUrlEncoded
    @POST("uapi/shop/basket")
    fun addToBasket(@FieldMap map: HashMap<String, String>): Observable<BasketItems>

    @GET("uapi/shop/basket/")
    fun getGoodsInBasket(@QueryMap map: HashMap<String, String>): Observable<BasketItems>

    @DELETE("uapi/shop/basket/")
    fun deleteItemInBasket(@QueryMap map: HashMap<String, String>): Observable<BasketItems>

    @GET("uapi/shop/request")
    fun getGoodDescription(@QueryMap map: HashMap<String, String>): Call<AboutGood>

    @GET("uapi/shop/request")
    fun getGood(@QueryMap map: HashMap<String, String>): Call<Good>

    //add to item to BASKET
    @FormUrlEncoded
    @POST("uapi/shop/wishlisth")
    fun addToWishList(@FieldMap map: HashMap<String, String>): Observable<WishList>

    @GET("uapi/shop/request")
    fun getWishList(@QueryMap map: HashMap<String, String>): Observable<WishList>

    @GET("uapi/shop/checkout/")
    fun getCheckout(@QueryMap param: HashMap<String, String>): Observable<CheckoutAllGoods>

    @FormUrlEncoded
    @PUT("uapi/shop/checkout/")
    fun recountCheckoutData(@FieldMap param: HashMap<String, String>): Single<CheckoutAllGoods>

    @GET("uapi/shop/invoices/")
    fun getInvoises(@QueryMap param: HashMap<String, String>): Observable<Orders>

    @FormUrlEncoded
    @POST("uapi/shop/checkout/")
    fun postCheckout(@FieldMap(encoded = true) map: HashMap<String, String>): Single<CheckoutResponse>

    @POST("v2.0/json/")
    fun searhCity(@Body body: City): Flowable<CityResponse>

    @POST("v2.0/json/")
    fun getAreas(@Body body: AreasRequest): Observable<AreasResponse>

    @POST("v2.0/json/")
    fun getWarehouses(@Body body: WarehouseRequest): Flowable<WarehouseResponse>

}
