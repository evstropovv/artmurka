package com.artmurka.artmurkaapp.Presenter;

import android.content.Context;

import com.artmurka.artmurkaapp.BuildConfig;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.ISendEmailPresenter;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.ISendEmailFragment;
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
