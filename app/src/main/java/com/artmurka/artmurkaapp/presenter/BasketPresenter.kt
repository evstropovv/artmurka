package com.artmurka.artmurkaapp.presenter


import com.artmurka.artmurkaapp.data.model.interfacesmodel.IBasket
import com.artmurka.artmurkaapp.data.model.modules.BasketRequest
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.BasketItems
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IBasketPresenter
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IBasketFragment


import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class BasketPresenter   :  BasePresenter<> {


    override fun getDataForbasket() {

        val basket = BasketRequest()
        val observable = basket.itemInBasket
        observable.subscribe(object : Observer<BasketItems> {
            override fun onSubscribe(d: Disposable) {}

            override fun onNext(value: BasketItems) {
                if (fragment != null) {
                    fragment!!.showItemsInBasket(value.success.basket.items)
                    fragment!!.makeMessageInvisible(true)
                    fragment!!.showPrice(value.success.basket.total)
                }
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {}
        })
    }

    override fun onDetach() {
        fragment = null
    }
}
