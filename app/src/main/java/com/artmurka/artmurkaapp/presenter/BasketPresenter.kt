package com.artmurka.artmurkaapp.presenter


import android.util.Log
import com.artmurka.artmurkaapp.data.model.modules.BasketRequest
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.BasketItems
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IBasketFragment
import com.artmurka.artmurkaapp.data.model.modules.CheckoutRequest
import com.artmurka.artmurkaapp.data.model.modules.WishListRequest
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.CheckoutAllGoods

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject


class BasketPresenter @Inject constructor(val basket: BasketRequest,
                                          val wishListRequest: WishListRequest,
                                          val checkoutRequest: CheckoutRequest) : BasePresenter<IBasketFragment>() {


    fun getDataForbasket() {

        val observable = basket.itemInBasket
        observable.subscribe(object : Observer<BasketItems> {
            override fun onSubscribe(d: Disposable) {}

            override fun onNext(value: BasketItems) {
                if (view != null) {
                    view!!.showItemsInBasket(value.success.basket.items)
                    view!!.makeMessageInvisible(true)
                    view!!.showPrice(value.success.basket.total)
                }
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {}
        })
    }

    fun onRefreshItem(position: String, id: String) {
        checkoutRequest.recountCheckoutData(position, id)
                .subscribeOn( Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread() )
                .subscribe({t->}, { error->
                    Log.e("Log.e", error.message)
                } )
    }

    fun addToWishList(id: String) {
        wishListRequest.toWishList(id)
    }


}
