package com.artmurka.artmurkaapp.android.views.fragments.interfaces

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription
import com.artmurka.artmurkaapp.presenter.PresenterView


interface IWishFragment  : PresenterView {
    fun showWishList(list: MutableList<GoodsListDescription>)
    fun deleteFromWisList(goodId : String)
}
