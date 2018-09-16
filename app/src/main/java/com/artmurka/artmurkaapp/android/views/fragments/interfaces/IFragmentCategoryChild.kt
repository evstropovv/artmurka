package com.artmurka.artmurkaapp.android.views.fragments.interfaces

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Success
import com.artmurka.artmurkaapp.presenter.PresenterView

import java.util.ArrayList

interface IFragmentCategoryChild  : PresenterView {
    fun showCategories(categoriesList: ArrayList<Success>)
    fun showError(msg: String)
}
