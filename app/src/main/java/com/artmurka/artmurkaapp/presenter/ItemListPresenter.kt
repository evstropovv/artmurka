package com.artmurka.artmurkaapp.presenter

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.GoodsProperties
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.SuccessExample
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IPresenterItemList
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IItemListFragment
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.Basket
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.Success
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription
import com.artmurka.artmurkaapp.domain.usecase.basket.ToBasketUseCase
import com.artmurka.artmurkaapp.domain.usecase.itemlist.GetItemListUseCase
import com.artmurka.artmurkaapp.domain.usecase.wishlist.ToWishListUseCase
import java.util.ArrayList
import java.util.TreeMap
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class ItemListPresenter @Inject constructor(val getItemtListUseCase: GetItemListUseCase,
                                            val toWishListUseCase: ToWishListUseCase,
                                            val toBasketUseCase: ToBasketUseCase) : BasePresenter<IItemListFragment>(), IPresenterItemList {


    private val goodsProperties: ArrayList<GoodsProperties>
    private var isFull = false

    init {
        goodsProperties = ArrayList()
    }

    fun toWishList(goodsId: String) {
        toWishListUseCase.execute(object : DisposableObserver<List<GoodsListDescription>>() {
            override fun onComplete() {}
            override fun onNext(t: List<GoodsListDescription>) {
                // view?.showWishList(t as MutableList<GoodsListDescription>)
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


    override fun getCategoriesData(curPage: Int, url: String, sort: String, order: String) {
        if (!isFull) {
            getItemtListUseCase.execute(object : DisposableObserver<Success>() {
                override fun onNext(value: Success) {
                    view?.apply {
                        showItemList(getList(value.goodsList!!))
                        setTitle(value.catName!!)
                        stopProgressBar()
                    }
                }

                override fun onComplete() {
                    view?.stopProgressBar()
                }

                override fun onError(e: Throwable) {
                    view?.apply {
                        isFull = true
                        stopProgressBar()
                    }
                }
            }, GetItemListUseCase.Params(url, curPage.toString(), sort, order))

        } else {
            view?.stopProgressBar()
        }
    }

    private fun getList(map: TreeMap<String, GoodsProperties>): ArrayList<GoodsProperties> {
        for (key in map.keys) {
            goodsProperties.add(map[key]!!)
        }
        return goodsProperties
    }

    override fun onDropView() {
        getItemtListUseCase.dispose()
        toWishListUseCase.dispose()
        toBasketUseCase.dispose()
        super.onDropView()
    }

}
