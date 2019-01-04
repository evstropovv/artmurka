package com.artmurka.artmurkaapp.domain.usecase.basket

import com.artmurka.artmurkaapp.data.model.modules.UcozApiModule
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.Basket
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.BasketItems
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.Success
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.HashMap

class GetItemsInBasketUseCaseTest {

    lateinit var getItemsInBasketUseCase: GetItemsInBasketUseCase

    @Mock
    lateinit var apiModule: ApiRetrofit

    @Mock
    lateinit var ucoz: UcozApiModule

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        initTestSchedulers()

        getItemsInBasketUseCase = GetItemsInBasketUseCase(apiModule, ucoz)
    }

    @Test
    fun `repository get success`() {

        `when`(apiModule.getGoodsInBasket(HashMap()))
                .thenReturn(Observable.just(getBasketItems()))

        // when
        val testObserver = getItemsInBasketUseCase.buildUseCaseObservable(GetItemsInBasketUseCase.Params()).test()

        // then
        verify(apiModule).getGoodsInBasket(HashMap())

        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertValueCount(1)
        testObserver.assertValue(getBasketItems().success?.basket)

    }


    @Test
    fun `repository get fail`() {

        val throwable = Throwable()
        // get
        `when`(apiModule.getGoodsInBasket(HashMap()))
                .thenReturn(Observable.error(throwable))

        // when
        val testObserver = getItemsInBasketUseCase.buildUseCaseObservable(GetItemsInBasketUseCase.Params()).test()

        // then
        verify(apiModule).getGoodsInBasket(HashMap())

        testObserver.assertNoValues()
        testObserver.assertNotComplete()
        testObserver.assertError(throwable)
        testObserver.assertValueCount(0)
    }

    companion object {
        var item: BasketItems? = null
        private fun getBasketItems(): BasketItems {
            if (item==null) {
                val success = Success()
                val basket = Basket()
                item = BasketItems()
                success.basket = basket
                item?.success = success
            }

            return item!!
        }
    }


    private fun initTestSchedulers() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { h -> Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { h -> Schedulers.trampoline() }
    }
}