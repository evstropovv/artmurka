package com.artmurka.artmurkaapp.data.model.interfacesmodel

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.WishList

import retrofit2.Call

interface IWishList {
    val wishList: Call<WishList>
    fun toWishList(goods_id: String): Call<WishList>
}
