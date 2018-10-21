package com.artmurka.artmurkaapp.domain.usecase.wishlist

import com.artmurka.artmurkaapp.data.model.modules.UcozApiModule
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.WishList
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit
import com.artmurka.artmurkaapp.domain.usecase.base.AbsUseCaseObs
import io.reactivex.Observable
import java.util.ArrayList
import java.util.HashMap
import javax.inject.Inject

class ToWishListUseCase @Inject constructor(val apiModule: ApiRetrofit, val ucoz: UcozApiModule) : AbsUseCaseObs<List<GoodsListDescription>, ToWishListUseCase.Params>() {

    override fun buildUseCaseObservable(params: ToWishListUseCase.Params): Observable<List<GoodsListDescription>> {
        val mapForUcozModule = HashMap<String, String>()
        mapForUcozModule["goods_id"] = params.goods_id
        val confForRequest = ucoz["POST", "uapi/shop/wishlisth", mapForUcozModule]
        return apiModule.addToWishList(confForRequest) .map { t: WishList -> t.success?.goodsList }
                .map {
                    val answerList = ArrayList<GoodsListDescription>()
                    for (key in it.keys) {
                        answerList.add(it[key]!!)
                    }
                    answerList
                }.map {
                    list->list.sortedWith(compareBy({it.entryTitle},{it.entryDescription}))
                }
    }


    class Params(var goods_id: String)
}