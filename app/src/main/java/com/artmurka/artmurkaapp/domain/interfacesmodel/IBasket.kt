package com.artmurka.artmurkaapp.domain.interfacesmodel

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.BasketItems

import io.reactivex.Observable


interface IBasket {
    val itemInBasket: Observable<BasketItems>
    fun toBasket(goodId: String): Observable<BasketItems>
    fun deleteItemFromBasket(goodId: String): Observable<BasketItems>
}
