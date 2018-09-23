package com.artmurka.artmurkaapp.presenter.interfaces_presenter


import java.util.HashMap

interface ICategorySettingsPresenter {
    fun applyChanges(settings: HashMap<String, String>, list: Int)

}
