package com.artmurka.artmurkaapp.presenter.interfaces_presenter


interface IAboutGoodsPresenter {
    fun getDataAboutGoods(id: String)
    fun getCategoryData(category: String)
    fun btnClicked(buttonId: Int)
}
