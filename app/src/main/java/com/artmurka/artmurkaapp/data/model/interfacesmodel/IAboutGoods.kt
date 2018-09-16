package com.artmurka.artmurkaapp.data.model.interfacesmodel

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.aboutgoods.AboutGood
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.good.Good
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.SuccessExample

import io.reactivex.Observable
import retrofit2.Call

interface IAboutGoods {
    fun getDataAboutGood(id: String): Call<AboutGood>
    fun getDataGood(id: String): Call<Good>
    fun getItemList(page: String, pageNumber: String): Observable<SuccessExample>
}
