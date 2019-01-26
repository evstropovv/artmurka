package com.artmurka.artmurkaapp.presenter

import com.artmurka.artmurkaapp.TestSchedulersRule
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IWishFragment
import com.artmurka.artmurkaapp.data.model.modules.UcozApiModule
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.Success
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.WishList
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit
import com.artmurka.artmurkaapp.domain.usecase.basket.ToBasketUseCase
import com.artmurka.artmurkaapp.domain.usecase.wishlist.GetWishListUseCase
import com.artmurka.artmurkaapp.domain.usecase.wishlist.ToWishListUseCase
import com.nhaarman.mockitokotlin2.any
import io.reactivex.Observable
import org.junit.Test

import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class WishPresenterTest {
    @Rule
    @JvmField
    val testScheduers = TestSchedulersRule()

    @Mock
    lateinit var apiModule: ApiRetrofit

    @Mock
    lateinit var ucoz: UcozApiModule

    @Mock
    lateinit var mockView: IWishFragment

    lateinit var getwishListUseCase: GetWishListUseCase

    lateinit var presenter: WishPresenter

    lateinit var toWishListUseCase: ToWishListUseCase

    lateinit var toBasketUseCase: ToBasketUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        toWishListUseCase = ToWishListUseCase(apiModule, ucoz)
        getwishListUseCase = GetWishListUseCase(apiModule, ucoz)
        toBasketUseCase = ToBasketUseCase(apiModule, ucoz)
        presenter = WishPresenter(getwishListUseCase, toWishListUseCase, toBasketUseCase)
        presenter.takeView(mockView)

    }

    @Test
    @Ignore //todo fix it
    fun getDataForWishList() {
        `when`(apiModule.getWishList(any())).thenReturn(Observable.just(getWishList()))
        `when`(getwishListUseCase
                .buildUseCaseObservable(GetWishListUseCase.Params()))
                .thenReturn(getNotNullOrderObservable())
        presenter.getDataForWishList()
        verify(mockView).showProgress()
        verify(mockView).hideProgress()
        verify(mockView).showWishList(any())
        verify(mockView, Mockito.never()).showError(ArgumentMatchers.anyString())
    }

    companion object {
        fun getNotNullOrderObservable(): Observable<List<GoodsListDescription>> {
            return Observable.just(listOf(GoodsListDescription()))
        }

        var wishList1: WishList? = null
        var success: Success? = null

        fun getWishList(): WishList {
            if (wishList1 == null) {
                wishList1 = WishList()
                success = Success()
                wishList1?.success = success
                success?.goodsList = HashMap<String, GoodsListDescription>()
            }
            return wishList1!!
        }

    }

}