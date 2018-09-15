package com.artmurka.artmurkaapp.presenter;

import android.content.Context;

import com.artmurka.artmurkaapp.BuildConfig;
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.ISendEmailPresenter;
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ISendEmailFragment;
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;


public class SendEmailPresenter implements ISendEmailPresenter {
    private ISendEmailFragment fragment;
    private Context ctx;

    public SendEmailPresenter(ISendEmailFragment fragment, Context ctx) {
        this.fragment = fragment;
        this.ctx = ctx;
    }

    @Override
    public void sendEmail(String msg) {
        BackgroundMail.newBuilder(ctx)
                .withUsername(BuildConfig.APPLICATION_EMAIL)
                .withPassword(BuildConfig.APPLICATION_EMAIL_PASSWORD)
                .withMailto(BuildConfig.SEND_TO_EMAIL)
                .withType(BackgroundMail.TYPE_PLAIN)
                .withSubject("Індивідуальне замовлення")
                .withBody(msg)
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        fragment.allOk();
                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        fragment.error();
                    }
                })
                .send();
    }

    @Override
    public void sendEmail(String msg, String fileuri) {
        BackgroundMail.newBuilder(ctx)
                .withUsername(BuildConfig.APPLICATION_EMAIL)
                .withPassword(BuildConfig.APPLICATION_EMAIL_PASSWORD)
                .withMailto(BuildConfig.SEND_TO_EMAIL)
                .withType(BackgroundMail.TYPE_PLAIN)
                .withAttachments(fileuri)
                .withSubject("Індивідуальне замовлення + додаток")
                .withBody(msg)
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        fragment.allOk();
                        fragment.clear();
                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        fragment.error();
                    }
                })
                .send();
    }
}
