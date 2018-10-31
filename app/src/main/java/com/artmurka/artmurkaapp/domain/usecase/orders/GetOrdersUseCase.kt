package com.artmurka.artmurkaapp.domain.usecase.orders

import com.artmurka.artmurkaapp.data.model.modules.UcozApiModule
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.orders.Orders
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit
import com.artmurka.artmurkaapp.domain.usecase.base.AbsUseCaseObs
import io.reactivex.Observable
import java.util.HashMap
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(val apiModule: ApiRetrofit, val ucoz: UcozApiModule) : AbsUseCaseObs<Orders, GetOrdersUseCase.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<Orders> {
        val mapForUcozModule = HashMap<String, String>()
        mapForUcozModule["sort"] = "id"
        mapForUcozModule["order"] = "desc"
        val confForRequest = ucoz.get("GET", "uapi/shop/invoices/", mapForUcozModule)
        return apiModule.getInvoises(confForRequest)
    }

    class Params

}