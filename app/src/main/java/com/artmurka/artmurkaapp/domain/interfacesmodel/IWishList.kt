package com.artmurka.artmurkaapp.domain.interfacesmodel

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.WishList
import io.reactivex.Observable

interface IWishList {
    val wishList: Observable<WishList>
    fun toWishList(goods_id: String): Observable<WishList>
}
