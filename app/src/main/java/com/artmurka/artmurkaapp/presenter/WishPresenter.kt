package com.artmurka.artmurkaapp.presenter

import android.util.Log
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IWishPresenter
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IWishFragment
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.Basket
import com.artmurka.artmurkaapp.domain.usecase.basket.ToBasketUseCase
import com.artmurka.artmurkaapp.domain.usecase.wishlist.GetWishListUseCase
import com.artmurka.artmurkaapp.domain.usecase.wishlist.ToWishListUseCase
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject


class WishPresenter @Inject constructor(val getWishListUseCase: GetWishListUseCase, val toWishListUseCase: ToWishListUseCase, val toBasketUseCase : ToBasketUseCase) : BasePresenter<IWishFragment>(), IWishPresenter {

    fun deleteFromWishOnline(goodsId: String){
        Log.d("Log.d","deleteFromWishOnline $goodsId")
        //здесь по запросу toWishList - или удаляется если она есть, или добавляется если позиции в списке нет
        toWishListUseCase.execute(object : DisposableObserver<List<GoodsListDescription>>() {
            override fun onComplete() {}
            override fun onNext(t:  List<GoodsListDescription> ) {
             //   view?.showWishList(t as MutableList<GoodsListDescription>)
            }
            override fun onError(e: Throwable) {  }
        }, ToWishListUseCase.Params(goodsId))


    }

    fun toBasket(goodId: String) {
        toBasketUseCase.execute(object : DisposableObserver<Basket>() {
            override fun onComplete() {}
            override fun onNext(t: Basket) {
               // view?.showWishList(t.items  )
               // view?.deleteFromWisList(t)
            //    t.items[1].
//TODO эта хрень должна как то протянуться до RvWishListAdapter
 //                                           Toast.makeText(ctx, wishList.get(position).getEntryTitle() + " успішно додано до кошика", Toast.LENGTH_SHORT).show();
//                                            String goodsId = wishList.get(position).getEntryId();
//                                            deleteFromWishOnline(goodsId);
//                                            wishList.remove(position);
//                                            notifyItemRemoved(position);
//                                            notifyItemRangeChanged(position, wishList.size());

            }
            override fun onError(e: Throwable) {  }
        }, ToBasketUseCase.Params(goodId))
    }

    override fun getDataForWishList() {
        getWishListUseCase.execute(object : DisposableObserver<List<GoodsListDescription>>() {
            override fun onNext(t: List<GoodsListDescription>) {
                view?.showWishList(t as MutableList<GoodsListDescription>)
            }

            override fun onComplete() { }

            override fun onError(e: Throwable) { }
        }, GetWishListUseCase.Params())

    }
}
