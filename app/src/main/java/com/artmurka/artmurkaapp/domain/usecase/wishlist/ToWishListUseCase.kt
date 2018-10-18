package com.artmurka.artmurkaapp.domain.usecase.wishlist

import com.artmurka.artmurkaapp.data.model.modules.UcozApiModule
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.WishList
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit
import com.artmurka.artmurkaapp.domain.usecase.base.AbsUseCaseObs
import io.reactivex.Observable
import java.util.HashMap
import javax.inject.Inject

class ToWishListUseCase @Inject constructor(val apiModule: ApiRetrofit, val ucoz: UcozApiModule) : AbsUseCaseObs<WishList, ToWishListUseCase.Params>() {

    override fun buildUseCaseObservable(params: ToWishListUseCase.Params): Observable<WishList> {
        val mapForUcozModule = HashMap<String, String>()
        mapForUcozModule["goods_id"] = params.goods_id
        val confForRequest = ucoz["POST", "uapi/shop/wishlisth", mapForUcozModule]
        return apiModule.addToWishList(confForRequest)
    }


    class Params(var goods_id: String)
}