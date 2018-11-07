package com.artmurka.artmurkaapp.presenter

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import android.widget.Toast
import com.artmurka.artmurkaapp.BuildConfig
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.activities.main.IMainActivity
import com.artmurka.artmurkaapp.data.model.databases.Preferences
import com.artmurka.artmurkaapp.other.FragmentType
import io.reactivex.Completable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainPresenter @Inject constructor(val ctx: Context) : BasePresenter<IMainActivity>() {

    internal var doubleBackToExitPressedOnce = false

    fun onBackPressed() {
        if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true
            view?.showToast(ctx.resources.getString(R.string.exit))
            Completable.complete().delay(2, TimeUnit.SECONDS).doOnComplete {
                doubleBackToExitPressedOnce = false
            }.subscribe()
        } else {

        }
    }

    fun onNavigationItemSelected(item: MenuItem) {
        val id = item.itemId
        when (id) {
            R.id.nav_catalog -> view?.changeFragment(FragmentType.CATEGORY_FRAGMENT)
            R.id.nav_basket -> view?.changeFragment(FragmentType.BASKET_FRAGMENT)
            R.id.nav_desires -> view?.changeFragment(FragmentType.WISH_FRAGMENT)
            R.id.nav_orders -> view?.changeFragment(FragmentType.ORDER_FRAGMENT)
            R.id.nav_individual -> view?.changeFragment(FragmentType.PAY_FRAGMENT)
            R.id.nav_consulting -> view?.makeCall()
            R.id.delivery -> view?.changeFragment(FragmentType.DELIVERY)

        }
    }

    fun showExitFromAccauntDialog() {

    }
}
