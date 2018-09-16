package com.artmurka.artmurkaapp.android.views.activities.checkout


import com.artmurka.artmurkaapp.presenter.PresenterView

interface ICheckoutActivity : PresenterView {
    fun changeFragment(currentFragment: Int)

}
