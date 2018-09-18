package com.artmurka.artmurkaapp.presenter

import com.artmurka.artmurkaapp.data.model.interfacesmodel.IOrderRequest
import com.artmurka.artmurkaapp.data.model.modules.OrdersRequests
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.orders.Orders
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IOrderPresenter
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IOrderFragment
import com.artmurka.artmurkaapp.data.model.modules.AllRequestOvservers

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class OrdersPresenter @Inject constructor(val request: OrdersRequests) : BasePresenter<IOrderFragment>(), IOrderPresenter {

    override fun getOrders() {
        val orders = request.orders
        orders.enqueue(object : Callback<Orders> {
            override fun onResponse(call: Call<Orders>, response: Response<Orders>) {
                view?.showOrders(response.body()!!)
            }

            override fun onFailure(call: Call<Orders>, t: Throwable) {

            }
        })
    }
}
