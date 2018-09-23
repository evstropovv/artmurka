package com.artmurka.artmurkaapp.presenter.interfaces_presenter


interface ICheckoutPresenter {
    fun getData()
    fun postCheckout(telephone: String, message: String, email: String, pay: String, delivery: String)
    fun cityChanged(msg: String)
    fun isEmailValid(email: String): Boolean?
    fun isValidPhone(phone: String): Boolean
    fun selectCity(cityPostition: Int?)
    fun getAreas()
    fun getCities(cityName: String)
    fun selectWarehouse(warehousePosition: Int?)
    fun detachView()
}
