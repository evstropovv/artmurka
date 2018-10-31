package com.artmurka.artmurkaapp.presenter

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.orders.Orders
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IOrderPresenter
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IOrderFragment
import com.artmurka.artmurkaapp.domain.usecase.orders.GetOrdersUseCase
import io.reactivex.observers.DisposableObserver

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class OrdersPresenter @Inject constructor(val request: GetOrdersUseCase) : BasePresenter<IOrderFragment>(), IOrderPresenter {

    override fun getOrders() {
        request.execute(object : DisposableObserver<Orders>() {
            override fun onComplete() {}

            override fun onNext(t: Orders) {
                view?.showOrders(t)
            }

            override fun onError(e: Throwable) {}
        }, GetOrdersUseCase.Params())
    }

    override fun onDropView() {
        request.dispose()
        super.onDropView()
    }
}
