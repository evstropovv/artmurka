package com.artmurka.artmurkaapp.domain.usecase.checkout

import com.artmurka.artmurkaapp.TestSchedulersRule
import com.artmurka.artmurkaapp.data.model.modules.UcozApiModule
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.CheckoutAllGoods
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.Success
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit
import io.reactivex.Observable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.HashMap

class PostCheckoutUseCaseTest {

    @Rule
    @JvmField
    val testScheduers = TestSchedulersRule()

    lateinit var useCase: GetCheckoutUseCase

    @Mock
    lateinit var apiModule: ApiRetrofit

    @Mock
    lateinit var ucoz: UcozApiModule

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetCheckoutUseCase(apiModule, ucoz)
    }

    @Test
    fun `repository get success`() {

        Mockito.`when`(apiModule.getCheckout(HashMap()))
                .thenReturn(Observable.just(getCheckoutItems()))

        // when
        val testObserver = useCase.buildUseCaseObservable(GetCheckoutUseCase.Params()).test()

        // then
        Mockito.verify(apiModule).getCheckout(HashMap())

        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertValueCount(1)
        testObserver.assertValue(getCheckoutItems().success)

    }

    @Test
    fun `repository get fail`() {

        val throwable = Throwable()
        // get.
        Mockito.`when`(apiModule.getCheckout(HashMap()))
                .thenReturn(Observable.error(throwable))

        // when
        val testObserver = useCase.buildUseCaseObservable(GetCheckoutUseCase.Params()).test()

        // then
        Mockito.verify(apiModule).getCheckout(HashMap())

        testObserver.assertNoValues()
        testObserver.assertNotComplete()
        testObserver.assertError(throwable)
        testObserver.assertValueCount(0)
    }

    companion object {
        var item: CheckoutAllGoods? = null
        private fun getCheckoutItems(): CheckoutAllGoods {
            if (item == null) {
                item = CheckoutAllGoods()
                val success = Success()
                item?.success = success
            }

            return item!!
        }
    }

}