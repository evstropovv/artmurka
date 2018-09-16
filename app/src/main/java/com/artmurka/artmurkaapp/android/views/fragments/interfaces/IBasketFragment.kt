package com.artmurka.artmurkaapp.android.views.fragments.interfaces

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.Item
import com.artmurka.artmurkaapp.presenter.PresenterView


interface IBasketFragment : PresenterView {
    fun showError(error: String)
    fun showItemsInBasket(items: List<Item>)
    fun makeMessageInvisible(b: Boolean)
    fun showPrice(price: String)
}
