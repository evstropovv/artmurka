package com.artmurka.artmurkaapp.presenter

import android.content.Context
import com.artmurka.artmurkaapp.Constants

import com.artmurka.artmurkaapp.presenter.interfaces_presenter.ISendEmailPresenter
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ISendEmailFragment
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail
import javax.inject.Inject


class SendEmailPresenter @Inject constructor(val context: Context) : BasePresenter<ISendEmailFragment>(),ISendEmailPresenter {

    override fun sendEmail(msg: String) {
        BackgroundMail.newBuilder(context)
                .withUsername(Constants.applicationemail())
                .withPassword(Constants.applicationemail_password())
                .withMailto(Constants.sendtoemail())
                .withType(BackgroundMail.TYPE_PLAIN)
                .withSubject("Індивідуальне замовлення")
                .withBody(msg)
                .withOnSuccessCallback { view?.allOk() }
                .withOnFailCallback { view?.error() }
                .send()
    }

    override fun sendEmail(msg: String, fileuri: String) {
        BackgroundMail.newBuilder(context)
                .withUsername(Constants.applicationemail())
                .withPassword(Constants.applicationemail_password())
                .withMailto(Constants.sendtoemail())
                .withType(BackgroundMail.TYPE_PLAIN)
                .withAttachments(fileuri)
                .withSubject("Індивідуальне замовлення + додаток")
                .withBody(msg)
                .withOnSuccessCallback {
                    view?.allOk()
                    view?.clear()
                }
                .withOnFailCallback { view?.error() }
                .send()
    }
}
