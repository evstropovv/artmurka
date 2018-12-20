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

class GetCategoriesUseCase @Inject constructor( val ucoz: UcozApiModule) : AbsUseCaseObs<ArrayList<Success>, GetCategoriesUseCase.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<ArrayList<Success>> {

        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.url())
                .addConverterFactory(GsonConverterFactory.create(
                        GsonBuilder().registerTypeAdapter(Success::class.java, SuccessDeserelised()).create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        val apiRetrofit = retrofit.create(ApiRetrofit::class.java)

        val mapForUcozModule = HashMap<String, String>()
        mapForUcozModule["page"] = "categories"
        val confForRequest = ucoz["GET", "uapi/shop/request", mapForUcozModule]
        return apiRetrofit.getShopCategories(confForRequest)
                .map { value -> value.success }
    }

    class Params

}