package com.artmurka.artmurkaapp.presenter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.activities.main.IMainActivity
import com.artmurka.artmurkaapp.other.Const
import com.artmurka.artmurkaapp.other.FragmentType
import io.reactivex.Completable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainPresenter @Inject constructor(val ctx: Context) : BasePresenter<IMainActivity>() {

    internal var doubleBackToExitPressedOnce = false

    fun onBackPressed() {
        if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = trueFLAG_ACTIVITY_NEW_TASK
            view?.showToast(ctx.resources.getString(R.string.exit))
            Completable.complete().delay(2, TimeUnit.SECONDS).doOnComplete {
                doubleBackToExitPressedOnce = false
            }.subscribe()
        } else {
            view?.exit()
        }
    }

    fun onNavigationItemSelected(item: MenuItem) {
        val id = item.itemId
        when (id) {
            R.id.categoryFragment -> view?.changeFragment(FragmentType.CATEGORY_FRAGMENT)
            R.id.basketFragment -> view?.changeFragment(FragmentType.BASKET_FRAGMENT)
            R.id.wishFragment -> view?.changeFragment(FragmentType.WISH_FRAGMENT)
            R.id.orderFragment -> view?.changeFragment(FragmentType.ORDER_FRAGMENT)
            R.id.individualFragment -> view?.changeFragment(FragmentType.PAY_FRAGMENT)
            R.id.nav_consulting -> makeCall()
            R.id.deliveryFragment -> view?.changeFragment(FragmentType.DELIVERY)
        }
    }


    fun makeCall() {
        val surf = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Const.TEL_NUMBER))
        surf.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        ctx.startActivity(surf)
    }

    fun showExitFromAccauntDialog() {
        io.reactivex.Flowable.just("1").onBackpressureLatest()
    }
}
