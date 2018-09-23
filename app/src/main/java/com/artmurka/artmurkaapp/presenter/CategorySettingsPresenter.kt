package com.artmurka.artmurkaapp.presenter

import com.artmurka.artmurkaapp.presenter.interfaces_presenter.ICategorySettingsPresenter
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ICategorySettings
import com.artmurka.artmurkaapp.data.model.modules.AllRequestOvservers

import java.util.HashMap
import javax.inject.Inject

class CategorySettingsPresenter @Inject constructor(val model: AllRequestOvservers): BasePresenter<ICategorySettings>(),ICategorySettingsPresenter {


    override fun applyChanges(settings: HashMap<String, String>, sort: Int) {

    }
}
