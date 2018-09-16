package com.artmurka.artmurkaapp.data.model.modules

import com.artmurka.artmurkaapp.BuildConfig
import com.artmurka.artmurkaapp.data.model.interfacesmodel.IAllRequestObservers
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Example
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Success
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.SuccessDeserelised
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import java.util.HashMap

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class AllRequestOvservers  @Inject constructor(val apiModule: ApiRetrofit, val ucoz : UcozApiModule ): IAllRequestObservers {

    override val categories: Observable<Example>
        get() {
            val mapForUcozModule = HashMap<String, String>()
            mapForUcozModule["page"] = "categories"
            val confForRequest = ucoz["GET", "uapi/shop/request", mapForUcozModule]
            val gson = GsonBuilder().registerTypeAdapter(Success::class.java, SuccessDeserelised()).create()

            val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            val apiRetrofit = retrofit.create(ApiRetrofit::class.java)

            return apiRetrofit.getShopCategories(confForRequest)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
        }
}
