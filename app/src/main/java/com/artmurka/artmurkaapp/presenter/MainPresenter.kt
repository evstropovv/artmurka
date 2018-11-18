package com.artmurka.artmurkaapp.presenter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import android.widget.Toast
import com.artmurka.artmurkaapp.BuildConfig
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.activities.main.IMainActivity
import com.artmurka.artmurkaapp.data.model.databases.Preferences
import com.artmurka.artmurkaapp.other.Const
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
            view?.exit()
        }
    }


    fun makeCall(){
        val surf = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Const.TEL_NUMBER))
        ctx.startActivity(surf)
    }

    fun showExitFromAccauntDialog() {

    }
}
