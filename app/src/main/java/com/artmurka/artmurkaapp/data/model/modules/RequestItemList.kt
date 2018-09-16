package com.artmurka.artmurkaapp.data.model.modules


import com.artmurka.artmurkaapp.data.model.interfacesmodel.IRequestItemList
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.SuccessExample
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit

import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.HashMap

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class RequestItemList  @Inject constructor(val apiModule: ApiRetrofit, val ucoz : UcozApiModule ) : IRequestItemList {

    override fun getItemList(page: String, pageNumber: String, sort: String, order: String): Observable<SuccessExample> {


        val mapForUcozModule = HashMap<String, String>()

        var encodePage: String? = null
        try {
            encodePage = URLEncoder.encode(page, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        mapForUcozModule["cat_uri"] = encodePage!!
        mapForUcozModule["pnum"] = pageNumber
        mapForUcozModule["sort"] = sort
        mapForUcozModule["order"] = order


        val confForRequest = ucoz.get("GET", "uapi/shop/cat", mapForUcozModule)
        confForRequest["cat_uri"] = page

        return apiModule.getItemList(confForRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
