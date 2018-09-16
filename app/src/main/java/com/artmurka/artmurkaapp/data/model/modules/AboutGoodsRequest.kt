package com.artmurka.artmurkaapp.data.model.modules

import com.artmurka.artmurkaapp.data.model.interfacesmodel.IAboutGoods
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.aboutgoods.AboutGood
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.good.Good
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.SuccessExample
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit

import java.util.HashMap

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import javax.inject.Inject

class AboutGoodsRequest @Inject constructor(val apiModule: ApiRetrofit, val ucoz : UcozApiModule ) : IAboutGoods {

    override fun getDataAboutGood(id: String): Call<AboutGood> {

        val mapForUcozModule = HashMap<String, String>()
        mapForUcozModule["page"] = "viewgoods"
        mapForUcozModule["id"] = id

        //Getting all token for autorization.
        val confForRequest = ucoz["GET", "uapi/shop/request", mapForUcozModule]
        confForRequest["page"] = mapForUcozModule["page"]!!

        return apiModule.getGoodDescription(confForRequest)
    }

    override fun getDataGood(id: String): Call<Good> {

        val mapForUcozModule = HashMap<String, String>()
        mapForUcozModule["page"] = "viewgoods"
        mapForUcozModule["id"] = id

        //Getting all token for autorization.
        val confForRequest = ucoz["GET", "uapi/shop/request", mapForUcozModule]
        confForRequest["page"] = mapForUcozModule["page"]!!

        return apiModule.getGood(confForRequest)
    }

    override fun getItemList(page: String, pageNumber: String): Observable<SuccessExample> {

        val mapForUcozModule = HashMap<String, String>()
        mapForUcozModule["cat_uri"] = page
        mapForUcozModule["pnum"] = pageNumber

        val confForRequest = ucoz["GET", "uapi/shop/cat", mapForUcozModule]
        confForRequest["cat_uri"] = page

        return apiModule.getItemList(confForRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
