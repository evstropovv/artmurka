package com.artmurka.artmurkaapp.presenter

import com.artmurka.artmurkaapp.data.model.modules.WishListRequest
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.WishList
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IWishPresenter
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IWishFragment
import java.util.ArrayList
import java.util.HashMap

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class WishPresenter @Inject constructor(val wishRequest: WishListRequest) : BasePresenter<IWishFragment>(), IWishPresenter {

    override fun getDataForWishList() {
        val call = wishRequest.wishList
        call.enqueue(object : Callback<WishList> {
            override fun onResponse(call: Call<WishList>, response: Response<WishList>) {
                try {
                    view?.showWishList(getList(response.body()!!.success.goodsList))
                } catch (e: NullPointerException) {
                }

            }

            override fun onFailure(call: Call<WishList>, t: Throwable) {}
        })
    }

    private fun getList(map: HashMap<String, GoodsListDescription>): List<GoodsListDescription> {
        val answerList = ArrayList<GoodsListDescription>()
        for (key in map.keys) {
            answerList.add(map[key]!!)
        }
        return answerList
    }

}
