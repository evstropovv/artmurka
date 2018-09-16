package com.artmurka.artmurkaapp.android.views.fragments.interfaces

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.orders.Orders
import com.artmurka.artmurkaapp.presenter.PresenterView

interface IOrderFragment  : PresenterView {
    fun showOrders(list: Orders)
}
