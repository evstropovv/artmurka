package com.artmurka.artmurkaapp.android.views.activities.main


import android.os.Bundle
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Success
import com.artmurka.artmurkaapp.other.FragmentType
import com.artmurka.artmurkaapp.presenter.PresenterView

interface IMainActivity : PresenterView {
    fun changeFragment(fragment: FragmentType, url: String? = null, childs: List<Success>? = null, catName: String? = null, bndl: Bundle? = null)
    fun showToast(msg: String)
    fun exit()
    fun makeCall()


}
