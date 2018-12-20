package com.artmurka.artmurkaapp.android.views.dialogs

import android.app.Activity
import android.os.Bundle
import android.view.Window
import com.artmurka.artmurkaapp.Constants
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.data.model.databases.Preferences
import kotlinx.android.synthetic.main.dialog_exit_ac.*

class ExitAcDialog(var c: Activity) : android.app.Dialog(c) {

    var exitAcDialogListener: ExitDialogListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_exit_ac)
        setCancelable(false)
        textView14.text = Preferences.name + c.resources.getString(R.string.dialog_exit_account)
        btnYes.text = c.resources.getString(R.string.yes)
        btnNo.text = c.resources.getString(R.string.no)
        initListeners()
    }

    private fun initListeners() {
        btnYes.setOnClickListener {
            Preferences.consumerKey = Constants.consumerkey()
            Preferences.consumerSecret = Constants.consumersecret()
            Preferences.oauthToken = Constants.oauthtoken()
            Preferences.oauthTokenSecret = Constants.oauthtokensecret()
            Preferences.name = "ArtMurka"
            Preferences.email = "artmurka.com"
            Preferences.isLogin = false
            exitAcDialogListener?.onYesPressed()
            dismiss()
        }
        btnNo.setOnClickListener {
            dismiss()
        }
    }

    interface ExitDialogListener {
        fun onYesPressed()
    }
}