package com.artmurka.artmurkaapp.data.model.modules

import com.artmurka.artmurkaapp.data.model.interfacesmodel.IWishList
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.WishList
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit

import java.util.HashMap

import retrofit2.Call
import javax.inject.Inject


class WishListRequest  @Inject constructor(val apiModule: ApiRetrofit, val ucoz : UcozApiModule ): IWishList {

    override val wishList: Call<WishList>
        get() {

            val mapForUcozModule = HashMap<String, String>()
            mapForUcozModule["page"] = "wishlist"
            val confForRequest = ucoz["GET", "uapi/shop/request", mapForUcozModule]
            return apiModule.getWishList(confForRequest)
        }

    override fun toWishList(goods_id: String): Call<WishList> {
        val mapForUcozModule = HashMap<String, String>()
        mapForUcozModule["goods_id"] = goods_id
        val confForRequest = ucoz["POST", "uapi/shop/wishlisth", mapForUcozModule]

        return apiModule.addToWishList(confForRequest)
        //                .subscribeOn(Schedulers.newThread())
        //                .observeOn(AndroidSchedulers.mainThread());
    }
}
