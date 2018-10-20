package com.artmurka.artmurkaapp.presenter

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IWishPresenter
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IWishFragment
import com.artmurka.artmurkaapp.domain.usecase.wishlist.GetWishListUseCase
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject


class WishPresenter @Inject constructor(val getWishListUseCase: GetWishListUseCase) : BasePresenter<IWishFragment>(), IWishPresenter {

    override fun getDataForWishList() {
        getWishListUseCase.execute(object : DisposableObserver<List<GoodsListDescription>>() {
            override fun onNext(t: List<GoodsListDescription>) {
                view?.showWishList(t)
            }

            override fun onComplete() { }

            override fun onError(e: Throwable) { }
        }, GetWishListUseCase.Params())

    }
}
