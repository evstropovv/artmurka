package com.artmurka.artmurkaapp.presenter.interfaces_presenter


interface ISendEmailPresenter {
    fun sendEmail(msg: String)
    fun sendEmail(msg: String, fileuri: String)
}
