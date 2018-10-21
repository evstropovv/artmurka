package com.artmurka.artmurkaapp.data.model.interfacesmodel

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.BasketItems

import io.reactivex.Observable
import retrofit2.Call


interface IBasket {
    val itemInBasket: Observable<BasketItems>
    fun toBasket(goodId: String): Observable<BasketItems>
    fun deleteItemFromBasket(goodId: String): Observable<BasketItems>
}
