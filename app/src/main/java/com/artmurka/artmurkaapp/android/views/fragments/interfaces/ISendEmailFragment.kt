package com.artmurka.artmurkaapp.android.views.fragments.interfaces

import com.artmurka.artmurkaapp.presenter.PresenterView


interface ISendEmailFragment  : PresenterView {
    fun allOk()
    fun error()
    fun clear()
}
