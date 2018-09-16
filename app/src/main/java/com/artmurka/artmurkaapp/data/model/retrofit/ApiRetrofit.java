package com.artmurka.artmurkaapp.data.model.retrofit;


import com.artmurka.artmurkaapp.data.model.pojo.itemlist.aboutgoods.AboutGood;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.*;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.CheckoutAllGoods;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkoutresponse.CheckoutResponse;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.good.Good;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.BasketItems;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.SuccessExample;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.Areas.AreasRequest;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.Areas.AreasResponse;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.CityRequest.City;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.CityResponse.CityResponse;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.WarehousesRequest.WarehouseRequest;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.WarehousesResponse.WarehouseResponse;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.orders.Orders;

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.WishList;

import java.util.HashMap;

import io.reactivex.Flowable;
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
    Call<CheckoutResponse> postCheckout(@FieldMap(encoded = true) HashMap<String, String> map);

    @POST("v2.0/json/")
    Flowable<CityResponse> searhCity(@Body City body);

    @POST("v2.0/json/")
    Observable<AreasResponse> getAreas(@Body AreasRequest body);

    @POST("v2.0/json/")
    Flowable<WarehouseResponse> getWarehouses(@Body WarehouseRequest body);

}
