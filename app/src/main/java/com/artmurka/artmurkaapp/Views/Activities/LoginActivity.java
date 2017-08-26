package com.artmurka.artmurkaapp.Views.Activities;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.artmurka.artmurkaapp.Model.Databases.Preferences;
import com.artmurka.artmurkaapp.Presenter.LoginUcoz.UcozApi;
import com.artmurka.artmurkaapp.R;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    private WebView webView;
    private String verifier;
    private OAuth1RequestToken requestToken = null;
    private OAuth10aService service;
    private OAuth1AccessToken accessToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        webView = (WebView) findViewById(R.id.webView);
        webView.clearCache(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        service = new ServiceBuilder("murka1")
                .apiSecret("DqUQJzeCPmwD9CRqbHo6sGBzKCb5U4")
                .debug()
                .build(UcozApi.instance());

        new AsyncTask<Void, Void, String>() {
            protected String doInBackground(Void... params) {
                try {
                    requestToken = service.getRequestToken();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                String url = service.getAuthorizationUrl(requestToken);
                return url;
            }

            @Override
            protected void onPostExecute(String result) {
                loadURL(result);
            }
        }.execute();


    }

    public void loadURL(final String url) {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                if (url.contains("oauth_verifier")) {
                    webView.setVisibility(webView.GONE);
                    Log.d("Log.d", url);
                    verifier = uri.getQueryParameter("oauth_verifier");
                    Toast.makeText(getApplicationContext(), verifier, Toast.LENGTH_SHORT).show();
                    getAccessToken();
                }
                return false;
            }
        });
        webView.loadUrl(url);
    }

    private void getAccessToken() {
        new AsyncTask<Void, Void, OAuth1AccessToken>() {
            protected OAuth1AccessToken doInBackground(Void... params) {
                try {
                    accessToken = service.getAccessToken(requestToken, verifier);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return accessToken;
            }

            @Override
            protected void onPostExecute(OAuth1AccessToken result) {
                Toast.makeText(getApplicationContext(), "Token = " + result.getToken() + "Secret = " + result.getTokenSecret(), Toast.LENGTH_LONG).show();
                Preferences.setConsumerKey("murka1");
                Preferences.setConsumerSecret("DqUQJzeCPmwD9CRqbHo6sGBzKCb5U4");
                Preferences.setOauthToken(result.getToken());
                Preferences.setOauthTokenSecret(result.getTokenSecret());
                finish();
            }
        }.execute();
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
