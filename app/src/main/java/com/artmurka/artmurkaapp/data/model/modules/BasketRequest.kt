package com.artmurka.artmurkaapp.data.model.modules

import com.artmurka.artmurkaapp.data.model.interfacesmodel.IBasket
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.BasketItems
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit


import java.util.HashMap

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import javax.inject.Inject


class BasketRequest @Inject constructor(val apiModule: ApiRetrofit, val ucoz : UcozApiModule ): IBasket {

    override val itemInBasket: Observable<BasketItems>
        get() {

            val mapForUcozModule = HashMap<String, String>()
            val confForRequest = ucoz.get("GET", "uapi/shop/basket/", mapForUcozModule)

            return apiModule.getGoodsInBasket(confForRequest)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
        }

    override fun toBasket(goodId: String): Observable<BasketItems> {
        val mapForUcozModule = HashMap<String, String>()
        mapForUcozModule["id"] = goodId
        mapForUcozModule["mode"] = "add"

        val confForRequest = ucoz.get("POST", "uapi/shop/basket", mapForUcozModule)

        return apiModule.addToBasket(confForRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteItemFromBasket(goodId: String): Call<BasketItems> {

        val mapForUcozModule = HashMap<String, String>()
        mapForUcozModule["goodId"] = goodId
        val confForRequest = ucoz.get("DELETE", "uapi/shop/basket/", mapForUcozModule)

        return apiModule.deleteItemInBasket(confForRequest)
        //                .subscribeOn(Schedulers.newThread())
        //                .observeOn(AndroidSchedulers.mainThread());

    }
}
