package com.artmurka.artmurkaapp.domain.usecase.checkout

import com.artmurka.artmurkaapp.TestSchedulersRule
import com.artmurka.artmurkaapp.data.model.modules.UcozApiModule
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkoutresponse.CheckoutResponse
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.net.URLEncoder
import java.util.HashMap

class PostCheckoutUseCaseTest {

    @Rule
    @JvmField
    val testScheduers = TestSchedulersRule()

    lateinit var useCase: PostCheckoutUseCase

    @Mock
    lateinit var apiModule: ApiRetrofit

    @Mock
    lateinit var ucoz: UcozApiModule

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = PostCheckoutUseCase(apiModule, ucoz)
    }

    @Test
    fun `repository get success`() {

        Mockito.`when`(apiModule.postCheckout(any()))
                .thenReturn(Single.just(getCheckoutSuccess()))
        Mockito.`when`(ucoz.get(anyOrNull(), anyOrNull(), anyOrNull()))
                .thenReturn(getUcozAnswer())

        // when
        val testObserver = useCase.buildUseCaseObservable(PostCheckoutUseCase.Params("phone", "msg", "email", "pay", "delivery")).test()

        // then
        Mockito.verify(apiModule).postCheckout(any())
        Mockito.verify(ucoz).get(any(), any(), any())

        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertValueCount(1)
        testObserver.assertValue(getCheckoutSuccess())

    }

    @Test
    fun `repository get fail`() {

        val throwable = Throwable()
        // get.
        Mockito.`when`(ucoz.get(anyOrNull(), anyOrNull(), anyOrNull()))
                .thenReturn(getUcozAnswer())
        Mockito.`when`(apiModule.postCheckout(any()))
                .thenReturn(Single.error(throwable))

        // when
        val testObserver = useCase.buildUseCaseObservable(PostCheckoutUseCase.Params("phone", "msg", "email", "pay", "delivery")).test()

        // then
        Mockito.verify(apiModule).postCheckout(any())
        Mockito.verify(ucoz).get(anyOrNull(), anyOrNull(), anyOrNull())

        testObserver.assertNoValues()
        testObserver.assertNotComplete()
        testObserver.assertError(throwable)
        testObserver.assertValueCount(0)
    }

    private fun getUcozAnswer(): HashMap<String, String> {
        val confForRequest2 = HashMap<String, String>()
        confForRequest2["oauth_signature"] = "d"
        confForRequest2["oauth_signature_method"]  = "a"
        confForRequest2["oauth_version"] = "1.0a"
        confForRequest2["oauth_consumer_key"] = "a"
        confForRequest2["oauth_token"] = "a"
        confForRequest2["oauth_nonce"] = "a"
        confForRequest2["oauth_timestamp"] = "12312"
        return confForRequest2
    }

    companion object {
        var itemSuccess: CheckoutResponse? = null
        var itemError: CheckoutResponse? = null

        private fun getCheckoutSuccess(): CheckoutResponse {
            if (itemSuccess == null) {
                itemSuccess = CheckoutResponse(CheckoutResponse.Success("2"), null)
            }
            return itemSuccess!!
        }

        private fun getCheckoutError(): CheckoutResponse {
            if (itemError == null) {
                itemError = CheckoutResponse(null, CheckoutResponse.Error("error"))
            }
            return itemError!!
        }
    }

}