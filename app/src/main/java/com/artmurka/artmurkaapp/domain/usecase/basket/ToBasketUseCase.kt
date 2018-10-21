package com.artmurka.artmurkaapp.domain.usecase.basket

import com.artmurka.artmurkaapp.data.model.modules.UcozApiModule
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.Basket
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.BasketItems
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit
import com.artmurka.artmurkaapp.domain.usecase.base.AbsUseCaseObs
import io.reactivex.Observable
import java.util.HashMap
import javax.inject.Inject

class ToBasketUseCase @Inject constructor(val apiModule: ApiRetrofit, val ucoz: UcozApiModule) : AbsUseCaseObs<Basket, ToBasketUseCase.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<Basket> {
        val mapForUcozModule = HashMap<String, String>()
        mapForUcozModule["id"] = params.goodId
        mapForUcozModule["mode"] = "add"

        val confForRequest = ucoz.get("POST", "uapi/shop/basket", mapForUcozModule)

        return apiModule.addToBasket(confForRequest)
                .map { t: BasketItems -> t.success?.basket }

    }
    class Params(val goodId: String)
}