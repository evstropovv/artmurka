package com.artmurka.artmurkaapp.presenter

import com.artmurka.artmurkaapp.TestSchedulersRule
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IOrderFragment
import com.artmurka.artmurkaapp.data.model.modules.UcozApiModule
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.orders.Orders
import com.artmurka.artmurkaapp.data.model.retrofit.ApiRetrofit
import com.artmurka.artmurkaapp.domain.usecase.orders.GetOrdersUseCase
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.junit.Rule
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.*
import org.mockito.Mockito

class OrdersPresenterTest {

    @Rule
    @JvmField
    val testScheduers = TestSchedulersRule()

    lateinit var presenter: OrdersPresenter

    lateinit var getOrdersUseCase: GetOrdersUseCase

    var orders = Orders()

    @Mock
    lateinit var apiModule: ApiRetrofit

    @Mock
    lateinit var ucoz: UcozApiModule

    @Mock
    lateinit var mockView: IOrderFragment

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        getOrdersUseCase = GetOrdersUseCase(apiModule, ucoz)

        presenter = OrdersPresenter(getOrdersUseCase)
        presenter.takeView(mockView)
    }


    @Test
    fun getOrdersTrue() {

        given(getOrdersUseCase.buildUseCaseObservable(GetOrdersUseCase.Params())).willReturn(getNotNullOrderObservable())

        presenter.getOrders()
        verify(mockView).showProgress()
        verify(mockView).hideProgress()
        verify(mockView).showOrders(orders)
        verify(mockView, Mockito.never()).showError(ArgumentMatchers.anyString())
    }

    @Test
    fun getOrdersError() {
        val mockedResponse: Throwable = mock(Throwable::class.java)

        `when`(getOrdersUseCase.buildUseCaseObservable(GetOrdersUseCase.Params()))
                .thenReturn(Observable.error(mockedResponse))

        presenter.getOrders()
        verify(mockView).showProgress()
        verify(mockView).hideProgress()
        verify(mockView, Mockito.never()).showError(ArgumentMatchers.anyString())
    }


    fun getNotNullOrderObservable(): Observable<Orders> {
        return Observable.just(orders)
    }
}