package com.artmurka.artmurkaapp.android.views.fragments.interfaces

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.GoodsProperties
import com.artmurka.artmurkaapp.presenter.PresenterView

import java.util.ArrayList

interface IFragmentAboutGoods  : PresenterView {

    fun setName(name: String)
    fun setPhoto(urles: ArrayList<String>)
    fun setPrice(price: String)
    fun setDescription(description: String)
    fun getDataForRecyclerView(category: String)
    fun setArticle(art: String)

    fun setFullDescription(fullDescription: String)
    fun setDataForRecyclerView(list: ArrayList<GoodsProperties>)
    fun setWishButton(isInWish: Boolean)
    fun setBasketButton(isInBasket: Boolean)

}
