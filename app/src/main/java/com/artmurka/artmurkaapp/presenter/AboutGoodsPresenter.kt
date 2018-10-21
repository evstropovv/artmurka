package com.artmurka.artmurkaapp.presenter

import android.text.Html
import android.util.Log

import com.artmurka.artmurkaapp.data.model.modules.AboutGoodsRequest
import com.artmurka.artmurkaapp.data.model.modules.BasketRequest
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.aboutgoods.AboutGood
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.aboutgoods.SizePhoto
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.good.Good
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.GoodsProperties
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.BasketItems
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.SuccessExample
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IAboutGoodsPresenter
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IFragmentAboutGoods
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.Basket
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.WishList
import com.artmurka.artmurkaapp.domain.usecase.basket.ToBasketUseCase
import com.artmurka.artmurkaapp.domain.usecase.wishlist.ToWishListUseCase

import java.util.ArrayList
import java.util.HashMap
import java.util.TreeMap

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AboutGoodsPresenter @Inject constructor(val model: AboutGoodsRequest,
                                              val model2: AboutGoodsRequest,
                                              val basket : BasketRequest,
                                              val toWishListUseCase: ToWishListUseCase,
                                              val toBasketUseCase: ToBasketUseCase ) : BasePresenter<IFragmentAboutGoods>(), IAboutGoodsPresenter {
    private var goodsId: String? = null

    override fun getDataAboutGoods(id: String) {
        this.goodsId = id
        val observable = model.getDataAboutGood(id)
        observable.enqueue(object : Callback<AboutGood> {
            override fun onResponse(call: Call<AboutGood>, response: Response<AboutGood>) {
                val aboutGood = response.body()!!.success
                view?.setName(aboutGood?.entryTitle!!)
                view?.setDescription(Html.fromHtml(aboutGood?.entryDescription).toString())
                view?.setPrice(aboutGood?.entryPrice?.priceRaw + " грн.")
                val map = aboutGood?.entryPhoto?.othersPhoto
                map!![aboutGood?.entryPhoto?.numPhotos.toString() + ""] = SizePhoto(aboutGood?.entryPhoto?.defPhoto?.photo)
                view?.setPhoto(getImageList(map))
                view?.getDataForRecyclerView(response.body()?.success?.entryCat?.url!!)
                view?.setWishButton(if (aboutGood.entryIsInWishlist == 1) true else false)
                view?.setBasketButton(if (aboutGood.entryIsInBasket > 0) true else false)
                Log.d("Log.d", "getdata - success")
            }

            override fun onFailure(call: Call<AboutGood>, t: Throwable) {
                try2(id)
                Log.d("Log.d", "getdata - fail")
                Log.d("Log.d", t.message)
            }
        })
    }

    private fun try2(id: String) {
        val observable = model2.getDataGood(id)
        observable.enqueue(object : Callback<Good> {
            override fun onResponse(call: Call<Good>, response: Response<Good>) {

                val aboutGood = response.body()!!.success
                view?.setName(aboutGood?.entryTitle!!)
                view?.setDescription(Html.fromHtml(aboutGood?.entryDescription).toString())
                view?.setPrice(aboutGood?.entryPrice?.priceRaw + " грн.")
                val map = HashMap<String, SizePhoto>()
                map[aboutGood?.entryPhoto?.numPhotos!!.toString() + ""] = SizePhoto(aboutGood?.entryPhoto?.defPhoto?.photo)
                view?.setPhoto(getImageList(map))
                view?.getDataForRecyclerView(response.body()?.success?.entryCat?.url!!)
                view?.setWishButton(if (aboutGood.entryIsInWishlist == 1) true else false)
                view?.setBasketButton(if (aboutGood.entryIsInBasket!! > 0) true else false)
                Log.d("Log.d", "getdata2 - success")
            }

            override fun onFailure(call: Call<Good>, t: Throwable) {
                Log.d("Log.d", "getdata2 - fail")
                Log.d("Log.d", t.message)
            }
        })
    }

    fun toWishList(goodsId: String) {
        toWishListUseCase.execute(object : DisposableObserver<List<GoodsListDescription>>() {
            override fun onComplete() {}
            override fun onNext(t: List<GoodsListDescription>) { }

            override fun onError(e: Throwable) {}
        }, ToWishListUseCase.Params(goodsId))
    }

    fun toBasket(goodId: String) {
        toBasketUseCase.execute(object : DisposableObserver<Basket>() {
            override fun onComplete() {}
            override fun onNext(t: Basket) { }

            override fun onError(e: Throwable) {}
        }, ToBasketUseCase.Params(goodId))
    }


    override fun getCategoryData(category: String) {
        val observable = model.getItemList(category, "1")
        observable.subscribe(object : Observer<SuccessExample> {
            override fun onSubscribe(d: Disposable) {}

            override fun onNext(value: SuccessExample) {
                view?.setDataForRecyclerView(getGoodsList(value?.success?.goodsList!!))
            }

            override fun onError(e: Throwable) {}

            override fun onComplete() {}
        })
    }

    override fun btnClicked(buttonId: Int) {
        when (buttonId) {
            R.id.ivWish -> {
                //добавить в список желаний
                toWishListUseCase.execute(object : DisposableObserver< List<GoodsListDescription> >() {
                    override fun onComplete() {}
                    override fun onNext(ts:  List<GoodsListDescription> ) {
                        try {

                            for (value in ts ) {
                                if (value.entryId == goodsId) {
                                    view?.setWishButton(true)
                                    break
                                } else {
                                    view?.setWishButton(false)
                                }
                            }
                        } catch (e: NullPointerException) {
                            view?.setWishButton(false)
                            e.printStackTrace()
                        }
                    }
                    override fun onError(e: Throwable) {  }
                }, ToWishListUseCase.Params(goodsId!!))
            }
            R.id.ivBasket -> {
                //кнопка добавления в корзину
                val observable = basket.toBasket(goodsId!!)
                observable.subscribe(object : Observer<BasketItems> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onNext(value: BasketItems) {
                        view?.setBasketButton(true)
                    }

                    override fun onError(e: Throwable) {}

                    override fun onComplete() {}
                })
            }
            else -> {
            }
        }
    }

    private fun getImageList(map: HashMap<String, SizePhoto>): ArrayList<String> {

        val photosUrl = ArrayList<String>()
        for (key in map.keys) {
            photosUrl.add(map[key]?.photo!!)
        }
        return photosUrl
    }


    private fun getGoodsList(map: TreeMap<String, GoodsProperties>): ArrayList<GoodsProperties> {
        val goodsProperties = ArrayList<GoodsProperties>()
        for (key in map.keys) {
            goodsProperties.add(map[key]!!)
        }
        return goodsProperties
    }
}
