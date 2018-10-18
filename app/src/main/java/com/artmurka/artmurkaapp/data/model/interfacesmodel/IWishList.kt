package com.artmurka.artmurkaapp.data.model.interfacesmodel

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.WishList
import io.reactivex.Observable

import retrofit2.Call

interface IWishList {
    val wishList: Observable<WishList>
    fun toWishList(goods_id: String): Observable<WishList>
}
