package com.artmurka.artmurkaapp.android.views.activities.main


import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Success
import com.artmurka.artmurkaapp.presenter.PresenterView

interface IMainActivity  : PresenterView {
    fun changeFragment(currentFragment: Int, url: String, childs: List<Success>, catName: String)
}
