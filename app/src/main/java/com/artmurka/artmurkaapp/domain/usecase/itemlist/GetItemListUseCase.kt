package com.artmurka.artmurkaapp.domain.usecase.itemlist

import com.artmurka.artmurkaapp.data.model.modules.UcozApiModule
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.SuccessExample
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.Success
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit
import com.artmurka.artmurkaapp.domain.usecase.base.AbsUseCaseObs
import io.reactivex.Observable
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.HashMap
import javax.inject.Inject

class GetItemListUseCase @Inject constructor(val apiModule: ApiRetrofit, val ucoz: UcozApiModule) : AbsUseCaseObs<Success, GetItemListUseCase.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<Success> {

        val mapForUcozModule = HashMap<String, String>()

        var encodePage: String? = null
        try {
            encodePage = URLEncoder.encode(params.page, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        mapForUcozModule["cat_uri"] = encodePage!!
        mapForUcozModule["pnum"] = params.pageNumber
        mapForUcozModule["sort"] = params.sort
        mapForUcozModule["order"] = params.order


        val confForRequest = ucoz.get("GET", "uapi/shop/cat", mapForUcozModule)
        confForRequest["cat_uri"] = params.page

        return apiModule.getItemList(confForRequest)
                .map { value->value.success }
    }

    class Params(val page: String, val pageNumber: String, val sort: String, val order: String)

}