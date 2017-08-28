package com.artmurka.artmurkaapp.Views.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.artmurka.artmurkaapp.Model.Databases.Preferences;
import com.artmurka.artmurkaapp.Presenter.LoginUcoz.UcozApi;
import com.artmurka.artmurkaapp.Presenter.LoginUcoz.User;
import com.artmurka.artmurkaapp.R;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    private WebView webView;
    private String verifier;
    private OAuth1RequestToken requestToken = null;
    private OAuth10aService service;
    private OAuth1AccessToken accessToken;
    private User user = null;

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
        deleteCokies();

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

    private void deleteCokies() {
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(this);
            cookieManager.removeAllCookie();
        } else {
            cookieManager.setAcceptCookie(true);
            cookieManager.removeAllCookies(new ValueCallback<Boolean>() {
                @Override
                public void onReceiveValue(Boolean value) {

                }
            });
        }
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

                //get user info
                OAuthRequest request = new OAuthRequest(Verb.GET, "http://uapi.ucoz.com/accounts/GetUserInfo");
                service.signRequest(accessToken, request);
                Response response = null;
                try {
                    response = service.execute(request);
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    user = gson.fromJson(response.getBody(), User.class);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Log.d("Log.d", response.getBody().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return accessToken;
            }

            @Override
            protected void onPostExecute(OAuth1AccessToken result) {
                Intent intent = new Intent();
                if (result.getToken() != null) {
                    Toast.makeText(getApplicationContext(), "Token = " + result.getToken() + "Secret = " + result.getTokenSecret(), Toast.LENGTH_LONG).show();
                    Preferences.setConsumerKey("murka1");
                    Preferences.setConsumerSecret("DqUQJzeCPmwD9CRqbHo6sGBzKCb5U4");
                    Preferences.setOauthToken(result.getToken());
                    Preferences.setOauthTokenSecret(result.getTokenSecret());
                    Preferences.setName(user.getNickname());
                    Preferences.setEmail(user.getEmail());
                    intent.putExtra("name", user.getNickname());
                    intent.putExtra("email", user.getEmail());

                } else {

                    intent.putExtra("name", "Арт-Мурка");
                    intent.putExtra("email", "artmurka.com");
                }
                setResult(RESULT_OK, intent);
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
