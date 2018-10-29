package com.artmurka.artmurkaapp.domain.usecase.checkout

import com.artmurka.artmurkaapp.data.model.modules.UcozApiModule
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.CheckoutAllGoods
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.Success
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkoutresponse.CheckoutResponse
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit
import com.artmurka.artmurkaapp.domain.usecase.base.AbsUseCaseObs
import com.artmurka.artmurkaapp.domain.usecase.base.AbsUseCaseSingle
import io.reactivex.Observable
import io.reactivex.Single
import java.util.HashMap
import javax.inject.Inject

class RecountCheckoutUseCase @Inject constructor(val apiModule: ApiRetrofit, val ucoz: UcozApiModule) : AbsUseCaseSingle<CheckoutAllGoods, RecountCheckoutUseCase.Params>() {

    override fun buildUseCaseObservable(params: Params): Single<CheckoutAllGoods> {
        val mapForUcozModule = HashMap<String, String>()
        mapForUcozModule["mode"] = "recalc"
        mapForUcozModule["cnt_${params.position}"] = params.id
        val confForRequest = ucoz.get("PUT", "uapi/shop/checkout/", mapForUcozModule)
        return apiModule.recountCheckoutData(confForRequest)
    }

    class Params(val position: String, val id: String)
}