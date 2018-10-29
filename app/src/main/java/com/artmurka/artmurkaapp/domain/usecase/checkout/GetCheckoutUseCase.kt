package com.artmurka.artmurkaapp.domain.usecase.checkout

import com.artmurka.artmurkaapp.data.model.modules.UcozApiModule
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit
import com.artmurka.artmurkaapp.domain.usecase.base.AbsUseCaseObs
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.Success
import io.reactivex.Observable
import java.util.HashMap
import javax.inject.Inject

class GetCheckoutUseCase @Inject constructor(val apiModule: ApiRetrofit, val ucoz: UcozApiModule) : AbsUseCaseObs<Success, GetCheckoutUseCase.Params>() {


    override fun buildUseCaseObservable(params: Params): Observable<Success> {
        val mapForUcozModule = HashMap<String, String>()
        val confForRequest = ucoz.get("GET", "uapi/shop/checkout/", mapForUcozModule)

        return apiModule.getCheckout(confForRequest).map { resp -> resp.success }
    }


    class Params()

}