package com.artmurka.artmurkaapp.android.views.fragments.interfaces

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.GoodsProperties
import com.artmurka.artmurkaapp.presenter.PresenterView

import java.util.ArrayList

interface IItemListFragment  : PresenterView {
    fun showItemList(goodsProperties: ArrayList<GoodsProperties>)  //какой то эррейлист
    fun showError(error: String)
    fun setTitle(title: String)
    fun stopProgressBar()
}
