package com.artmurka.artmurkaapp.Model.Retrofit;


import com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods.AboutGood;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Areas.Areas;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Categories.*;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.CheckoutAllGoods;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Good.Good;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemBasket.BasketItems;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemList.SuccessExample;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.Areas.AreasRequest;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.Areas.AreasResponse;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityRequest.City;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityResponse.CityResponse;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Orders.Orders;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.WishList.WishList;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;

public interface ApiRetrofit {

    @GET("uapi/shop/request")
    Observable<Example> getShopCategories(@QueryMap HashMap<String, String> map);

    //oauth_signature oauth_signature_method oauth_version oauth_consumer_key oauth_token oauth_nonce oauth_timestamp page
    @GET("uapi/shop/cat")
    Observable<SuccessExample> getItemList(@QueryMap HashMap<String, String> map);

    //add to item to BASKET
    @FormUrlEncoded
    @POST("uapi/shop/basket")
    Observable<BasketItems> addToBasket(@FieldMap HashMap<String, String> map);

    @GET("uapi/shop/basket/")
    Observable<BasketItems> getGoodsInBasket(@QueryMap HashMap<String, String> map);

    @DELETE("uapi/shop/basket/")
    Call<BasketItems> deleteItemInBasket(@QueryMap HashMap<String, String> map);

    @GET("uapi/shop/request")
    Call<AboutGood> getGoodDescription(@QueryMap HashMap<String, String> map);

    @GET("uapi/shop/request")
    Call<Good> getGood(@QueryMap HashMap<String, String> map);

    //add to item to BASKET
    @FormUrlEncoded
    @POST("uapi/shop/wishlisth")
    Call<WishList> addToWishList(@FieldMap HashMap<String, String> map);

    @GET("uapi/shop/request")
    Call<WishList> getWishList(@QueryMap HashMap<String, String> map);

    @GET("uapi/shop/checkout/")
    Observable<CheckoutAllGoods> getCheckout(@QueryMap HashMap<String, String> param);

    @FormUrlEncoded
    @PUT("uapi/shop/checkout/")
    Call<CheckoutAllGoods> recountCheckoutData(@FieldMap HashMap<String, String> param);

    @GET("uapi/shop/invoices/")
    Call<Orders> getInvoises(@QueryMap HashMap<String, String> param);

    @FormUrlEncoded
    @POST("uapi/shop/checkout/")
    Call<com.artmurka.artmurkaapp.Model.Pojo.ItemList.Categories.Success> postCheckout(@FieldMap(encoded = true) HashMap<String, String> map);

    @POST("v2.0/json/")
    Observable<CityResponse> searhCity(@Body City body);

    @POST("v2.0/json/")
    Observable<AreasResponse> getAreas(@Body AreasRequest body);

    @POST("v2.0/json/")
    Observable<AreasResponse> getWarehouses(@Body AreasRequest body);

}
