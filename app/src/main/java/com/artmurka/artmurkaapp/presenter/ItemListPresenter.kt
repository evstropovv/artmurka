package com.artmurka.artmurkaapp.presenter

import android.util.Log

import com.artmurka.artmurkaapp.data.model.modules.RequestItemList
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.GoodsProperties
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.SuccessExample
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IPresenterItemList
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IItemListFragment
import java.util.ArrayList
import java.util.TreeMap
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ItemListPresenter @Inject constructor(val model: RequestItemList) : BasePresenter<IItemListFragment>(), IPresenterItemList {


    private val goodsProperties: ArrayList<GoodsProperties>
    private var isFull = false

    init {
        goodsProperties = ArrayList()
    }


    override fun getCategoriesData(curPage: Int,  url: String, sort: String, order: String) {
        if (!isFull) {
            exampleObservable = model.getItemList(url, curPage.toString(), sort, order)
            exampleObservable!!.subscribe(object : Observer<SuccessExample> {
                override fun onSubscribe(d: Disposable) {}

                override fun onNext(value: SuccessExample) {
                    if (view != null) {
                        view!!.showItemList(getList(value.success.goodsList))
                        view!!.setTitle(value.success.catName)
                        view!!.stopProgressBar()
                    }
                }

                override fun onError(e: Throwable) {
                    if (view != null) {
                        isFull = true
                        view?.stopProgressBar()
                        Log.d("Log.d", e.message)
                    }
                }

                override fun onComplete() {
                    view?.stopProgressBar()
                }
            })
        } else {
            view?.stopProgressBar()
        }
    }

    private fun getList(map: TreeMap<String, GoodsProperties>): ArrayList<GoodsProperties> {
        for (key in map.keys) {
            Log.d("Log.d", key + " " + map[key]?.getEntryPrice()?.priceRaw)
            goodsProperties.add(map[key]!!)
        }
        return goodsProperties
    }

    companion object {
        private var exampleObservable: Observable<SuccessExample>? = null
    }
}
