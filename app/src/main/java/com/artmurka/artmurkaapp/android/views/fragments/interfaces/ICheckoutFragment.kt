package com.artmurka.artmurkaapp.android.views.fragments.interfaces


import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.OrderDesc
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.Areas.AreasResponse
import com.artmurka.artmurkaapp.presenter.PresenterView

import java.util.ArrayList

interface ICheckoutFragment  : PresenterView {
    fun showCheckout(list: List<OrderDesc>)
    fun refreshSumPrice(price: String)
    fun showOrderIsProcessed(msg: String)
    fun setDataSpinner(delivery: ArrayList<String>, payment: ArrayList<String>)
    fun setSityes(sityes: Array<String>)
    fun showMessage(message: String)
    fun setAreas(areas: AreasResponse)
    fun setCities(cities: List<String>)
    fun setWarehouses(warehouses: List<String>)
    fun showDialog(msg: String)
}
