package com.artmurka.artmurkaapp.presenter

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IWishPresenter
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IWishFragment
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.Basket
import com.artmurka.artmurkaapp.domain.usecase.basket.ToBasketUseCase
import com.artmurka.artmurkaapp.domain.usecase.wishlist.GetWishListUseCase
import com.artmurka.artmurkaapp.domain.usecase.wishlist.ToWishListUseCase
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject


class WishPresenter @Inject constructor(val getWishListUseCase: GetWishListUseCase,
                                        val toWishListUseCase: ToWishListUseCase,
                                        val toBasketUseCase: ToBasketUseCase) : BasePresenter<IWishFragment>(), IWishPresenter {

    fun deleteFromWishOnline(goodsId: String) {
        //здесь по запросу toWishList - или удаляется если она есть, или добавляется если позиции в списке нет
        toWishListUseCase.execute(object : DisposableObserver<List<GoodsListDescription>>() {
            override fun onComplete() {}
            override fun onNext(t: List<GoodsListDescription>) {
            }

            override fun onError(e: Throwable) {}
        }, ToWishListUseCase.Params(goodsId))


    }

    fun toBasket(goodId: String) {
        toBasketUseCase.execute(object : DisposableObserver<Basket>() {
            override fun onComplete() {}
            override fun onNext(t: Basket) {}
            override fun onError(e: Throwable) {}
        }, ToBasketUseCase.Params(goodId))
    }

    override fun getDataForWishList() {
        view?.showProgress()
        getWishListUseCase.execute(object : DisposableObserver<List<GoodsListDescription>>() {
            override fun onNext(t: List<GoodsListDescription>) {
                view?.hideProgress()
                view?.showWishList(t as MutableList<GoodsListDescription>)
            }
            override fun onComplete() {}
            override fun onError(e: Throwable) {
                view?.hideProgress()
                view?.showError(e.message!!)
            }
        }, GetWishListUseCase.Params())

    }

    override fun onDropView() {
        getWishListUseCase.dispose()
        toWishListUseCase.dispose()
        toBasketUseCase.dispose()
        super.onDropView()
    }
}
