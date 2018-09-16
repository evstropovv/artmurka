package com.artmurka.artmurkaapp.presenter.interfaces_presenter

import com.artmurka.artmurkaapp.presenter.BasePresenter

/**
 * Created by Вася on 10.07.2017.
 */

interface IBasketPresenter : BasePresenter<MainView>() {
    fun getDataForbasket()

    fun onDetach()

}
