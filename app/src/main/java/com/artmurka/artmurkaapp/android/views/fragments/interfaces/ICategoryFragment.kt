package com.artmurka.artmurkaapp.android.views.fragments.interfaces

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Success
import com.artmurka.artmurkaapp.presenter.PresenterView


interface ICategoryFragment : PresenterView {

    fun showCategories(categoriesList: List<Success>)  //show categories
    fun setToolBarName(name: String)
    fun showError(error: String)  //show error, if error
}
