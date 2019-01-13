package com.artmurka.artmurkaapp.domain.usecase.categories

import com.artmurka.artmurkaapp.Constants
import com.artmurka.artmurkaapp.data.model.modules.UcozApiModule
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit
import com.artmurka.artmurkaapp.domain.usecase.base.AbsUseCaseObs
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Success
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.SuccessDeserelised
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Named

class GetCategoriesUseCase @Inject constructor( val ucoz: UcozApiModule, @Named("categories") val apiModule: ApiRetrofit) : AbsUseCaseObs<ArrayList<Success>, GetCategoriesUseCase.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<ArrayList<Success>> {
        val mapForUcozModule = HashMap<String, String>()
        mapForUcozModule["page"] = "categories"
        val confForRequest = ucoz["GET", "uapi/shop/request", mapForUcozModule]
        return apiModule.getShopCategories(confForRequest)
                .map { value -> value.success }
    }

    class Params

}