package com.artmurka.artmurkaapp.presenter

import android.util.Log
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.WishList
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IWishPresenter
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IWishFragment
import com.artmurka.artmurkaapp.domain.usecase.wishlist.GetWishListUseCase
import io.reactivex.observers.DisposableObserver
import java.util.ArrayList
import java.util.HashMap
import javax.inject.Inject


class WishPresenter @Inject constructor(val getWishListUseCase: GetWishListUseCase) : BasePresenter<IWishFragment>(), IWishPresenter {

    override fun getDataForWishList() {
        getWishListUseCase.execute(object : DisposableObserver<WishList>() {
            override fun onComplete() { }

            override fun onNext(t: WishList) {
                try {
                    view?.showWishList(getList(t.success.goodsList))
                } catch (e: NullPointerException) {
                    Log.e("Log.e", e.printStackTrace().toString())
                }
            }

            override fun onError(e: Throwable) {
            }
        }, GetWishListUseCase.Params())

    }

    private fun getList(map: HashMap<String, GoodsListDescription>): List<GoodsListDescription> {
        val answerList = ArrayList<GoodsListDescription>()
        for (key in map.keys) {
            answerList.add(map[key]!!)
        }
        return answerList
    }

}
