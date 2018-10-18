package com.artmurka.artmurkaapp.domain.usecase.wishlist

import com.artmurka.artmurkaapp.data.model.modules.UcozApiModule
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit
import com.artmurka.artmurkaapp.domain.usecase.base.AbsUseCaseObs
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.WishList
import io.reactivex.Observable
import java.util.HashMap
import javax.inject.Inject

class GetWishListUseCase @Inject constructor(val apiModule: ApiRetrofit, val ucoz : UcozApiModule) : AbsUseCaseObs<WishList, GetWishListUseCase.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<WishList> {
        val config = HashMap<String, String>()
        config["page"] = "wishlist"
        val confForRequest = ucoz["GET", "uapi/shop/request", config]
        return apiModule.getWishList(confForRequest)
    }

    class Params
}