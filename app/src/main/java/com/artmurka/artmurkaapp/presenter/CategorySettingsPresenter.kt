package com.artmurka.artmurkaapp.presenter

import com.artmurka.artmurkaapp.presenter.interfaces_presenter.ICategorySettingsPresenter
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ICategorySettings

import java.util.HashMap

class CategorySettingsPresenter: BasePresenter<ICategorySettings>(),ICategorySettingsPresenter {


    override fun applyChanges(settings: HashMap<String, String>, sort: Int) {

    }
}
