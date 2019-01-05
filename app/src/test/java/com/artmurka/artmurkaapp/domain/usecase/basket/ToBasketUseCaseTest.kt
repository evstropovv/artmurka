package com.artmurka.artmurkaapp.domain.usecase.basket

import com.artmurka.artmurkaapp.TestSchedulersRule
import com.artmurka.artmurkaapp.data.model.modules.UcozApiModule
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.Basket
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.BasketItems
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.Success
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.HashMap

class ToBasketUseCaseTest {

    @Rule
    @JvmField
    val testScheduers = TestSchedulersRule()

    lateinit var toBasketUseCase: ToBasketUseCase

    @Mock
    lateinit var apiModule: ApiRetrofit

    @Mock
    lateinit var ucoz: UcozApiModule

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        toBasketUseCase = ToBasketUseCase(apiModule, ucoz)
    }

    @Test
    fun `repository get success`() {

        Mockito.`when`(apiModule.addToBasket(HashMap()))
                .thenReturn(Observable.just(getBasketItems()))

        // when
        val testObserver = toBasketUseCase.buildUseCaseObservable(ToBasketUseCase.Params("1")).test()

        // then
        Mockito.verify(apiModule).addToBasket(HashMap())

        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertValueCount(1)
        testObserver.assertValue(getBasketItems().success?.basket)

    }


    @Test
    fun `repository get fail`() {

        val throwable = Throwable()
        // get
        Mockito.`when`(apiModule.addToBasket(HashMap()))
                .thenReturn(Observable.error(throwable))

        // when
        val testObserver = toBasketUseCase.buildUseCaseObservable(ToBasketUseCase.Params("1")).test()

        // then
        Mockito.verify(apiModule).addToBasket(HashMap())

        testObserver.assertNoValues()
        testObserver.assertNotComplete()
        testObserver.assertError(throwable)
        testObserver.assertValueCount(0)
    }

    companion object {
        var item: BasketItems? = null
        private fun getBasketItems(): BasketItems {
            if (item == null) {
                val success = Success()
                val basket = Basket()
                item = BasketItems()
                success.basket = basket
                item?.success = success
            }

            return item!!
        }
    }
}