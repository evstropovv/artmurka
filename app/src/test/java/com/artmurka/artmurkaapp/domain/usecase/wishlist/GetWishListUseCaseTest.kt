package com.artmurka.artmurkaapp.domain.usecase.wishlist

import com.artmurka.artmurkaapp.TestSchedulersRule
import com.artmurka.artmurkaapp.data.model.modules.UcozApiModule
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.Success
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.WishList
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit
import io.reactivex.Observable
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.ArrayList
import java.util.HashMap

class GetWishListUseCaseTest {

    @Rule
    @JvmField
    val testScheduers = TestSchedulersRule()

    lateinit var getWishListUseCase: GetWishListUseCase

    @Mock
    lateinit var apiModule: ApiRetrofit

    @Mock
    lateinit var ucoz: UcozApiModule

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getWishListUseCase = GetWishListUseCase(apiModule, ucoz)
    }

    @Test
    fun `repository get success`() {

        Mockito.`when`(apiModule.getWishList(HashMap()))
                .thenReturn(Observable.just(getWishListItems()))

        // when
        val testObserver = getWishListUseCase.buildUseCaseObservable(GetWishListUseCase.Params()).test()

        // then
        Mockito.verify(apiModule).getWishList(HashMap())

        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertValueCount(1)
        testObserver.assertValue(getSortedGoodsListDescription(getWishListItems()))

    }
    private fun getSortedGoodsListDescription(list : WishList) : List<GoodsListDescription> {
        var map = list.success?.goodsList
        val answerList = ArrayList<GoodsListDescription>()
        for (key in map?.keys!!) {
            answerList.add(map[key]!!)
        }
        return answerList.sortedWith(compareBy({it.entryTitle},{it.entryDescription}))
    }


    @Test
    fun `repository get fail`() {

        val throwable = Throwable()
        // get.
        Mockito.`when`(apiModule.getWishList(HashMap()))
                .thenReturn(Observable.error(throwable))

        // when
        val testObserver = getWishListUseCase.buildUseCaseObservable(GetWishListUseCase.Params()).test()

        // then
        Mockito.verify(apiModule).getWishList(HashMap())

        testObserver.assertNoValues()
        testObserver.assertNotComplete()
        testObserver.assertError(throwable)
        testObserver.assertValueCount(0)
    }

    companion object {
        var item: WishList? = null
        private fun getWishListItems(): WishList {
            if (item == null) {
                item = WishList()
                val success = Success()
                val list =  HashMap<String, GoodsListDescription>()
                success.goodsList = list
                item?.success = success
            }

            return item!!
        }
    }
}