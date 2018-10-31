package com.artmurka.artmurkaapp.presenter

import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IBasketFragment
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.CheckoutAllGoods
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.Basket
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription
import com.artmurka.artmurkaapp.domain.usecase.basket.GetItemsInBasketUseCase
import com.artmurka.artmurkaapp.domain.usecase.checkout.RecountCheckoutUseCase
import com.artmurka.artmurkaapp.domain.usecase.wishlist.ToWishListUseCase

import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject


class BasketPresenter @Inject constructor(val getItemIsBasketUseCase: GetItemsInBasketUseCase,
                                          val toWishListUseCase: ToWishListUseCase,
                                          val recountCheckoutUseCase: RecountCheckoutUseCase) : BasePresenter<IBasketFragment>() {


    fun getDataForbasket() {
        getItemIsBasketUseCase.execute(object : DisposableObserver<Basket>() {
            override fun onComplete() {}
            override fun onNext(t: Basket) {
                view?.showItemsInBasket(t.items!!)
                view?.makeMessageInvisible(true)
                view?.showPrice(t?.total!!)
            }

            override fun onError(e: Throwable) {}
        }, GetItemsInBasketUseCase.Params())
    }

    fun onRefreshItem(position: String, id: String) {
        recountCheckoutUseCase.execute(object : DisposableSingleObserver<CheckoutAllGoods>() {
            override fun onError(e: Throwable) {}
            override fun onSuccess(t: CheckoutAllGoods) {}
        }, RecountCheckoutUseCase.Params(position, id))
    }

    fun addToWishList(id: String) {
        toWishListUseCase.execute(object : DisposableObserver<List<GoodsListDescription>>() {
            override fun onComplete() {}
            override fun onNext(t: List<GoodsListDescription>) {}
            override fun onError(e: Throwable) {}
        }, ToWishListUseCase.Params(id))
    }

    override fun onDropView() {
        getItemIsBasketUseCase.dispose()
        recountCheckoutUseCase.dispose()
        toWishListUseCase.dispose()
        super.onDropView()
    }
}
