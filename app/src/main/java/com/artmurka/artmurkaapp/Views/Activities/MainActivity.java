package com.artmurka.artmurkaapp.Views.Activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.artmurka.artmurkaapp.Other.PayLiq;
import com.artmurka.artmurkaapp.Views.Fragments.BasketFragment;
import com.artmurka.artmurkaapp.Other.Const;
import com.artmurka.artmurkaapp.Views.Fragments.ItemListFragment;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Fragments.CategoryFragment;
import com.artmurka.artmurkaapp.Views.Fragments.OrderFragment;
import com.artmurka.artmurkaapp.Views.Fragments.WishFragment;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity implements IMainActivity, NavigationView.OnNavigationItemSelectedListener{

    CategoryFragment fragCategory;
    private final String TAG = "Storage_category_fragment";
    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadShopFragment();
        setUI();
        loadProfileTracker();
        if (Profile.getCurrentProfile()!=null) Toast.makeText(this, Profile.getCurrentProfile().getFirstName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void loadProfileTracker() {
        callbackManager = CallbackManager.Factory.create();

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    Profile oldProfile,
                    Profile currentProfile) {
                // App code

            }
        };
    }

    private void loadShopFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(TAG);
        if (fragment == null) {
            fragCategory = new CategoryFragment();
            fm.beginTransaction()
                    .replace(R.id.mainFrame, fragCategory, TAG)
                    //without backstacktrace
                    .commit();
            fm.executePendingTransactions();
        } else {
            fragCategory = (CategoryFragment) fragment;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.card) {
            changeFragment(Const.BASKET_FRAGMENT, null);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView tvProfileName = (TextView)header.findViewById(R.id.tvProfileName);

        //логин фейсбук
        LoginButton lb = (LoginButton)header.findViewById(R.id.login_button);
        lb.setReadPermissions("email");
        lb.setReadPermissions("public_profile");
//        CallbackManager callbackManager = CallbackManager.Factory.create();
//        lb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.d("Log.d","facebook access token: " + loginResult.getAccessToken());
//                Profile profile = Profile.getCurrentProfile();
//                tvProfileName.setText(profile.getName());
//            }
//
//            @Override
//            public void onCancel() {
//                Log.d("Log.d","facebook onCancel");
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Log.d("Log.d","facebook onError");
//            }
//        });

        if (Profile.getCurrentProfile()!=null) tvProfileName.setText(Profile.getCurrentProfile().getName());
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (fragCategory != null) {
            outState.putString(TAG, fragCategory.getTag());
        }
    }

    @Override
    public void changeFragment(int fragment,String url) {
        FragmentManager fm = getSupportFragmentManager();
        switch (fragment){
            case 101:
                CategoryFragment categoryFragment = new CategoryFragment();
                fm.beginTransaction()
                        .replace(R.id.mainFrame, categoryFragment)
                        .addToBackStack(TAG)
                        .commit();
                fm.executePendingTransactions();
                break;

            case 102:
                ItemListFragment itemList = new ItemListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                itemList.setArguments(bundle);
                fm.beginTransaction()
                        .replace(R.id.mainFrame, itemList)
                        .addToBackStack("a")
                        .commit();
                fm.executePendingTransactions();
                break;
            case 103:
                BasketFragment cartFragment = new BasketFragment();
                fm.beginTransaction()
                        .replace(R.id.mainFrame, cartFragment)
                        .addToBackStack("cartFragment")
                        .commit();
                fm.executePendingTransactions();
                break;
            case 104:
                WishFragment wishFragment = new WishFragment();
                fm.beginTransaction()
                        .replace(R.id.mainFrame, wishFragment)
                        .addToBackStack("wishFragment")
                        .commit();
                fm.executePendingTransactions();
                break;
            case 105:
                OrderFragment orderFragment = new OrderFragment();
                fm.beginTransaction()
                        .replace(R.id.mainFrame, orderFragment)
                        .addToBackStack("orderFragment")
                        .commit();
                fm.executePendingTransactions();
                break;
            case 111:
                new PayLiq(getBaseContext(),null).start();
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_catalog) { //каталог
            changeFragment(Const.CATEGORY_FRAGMENT, null);
            // Handle the camera action
        } else if (id == R.id.nav_basket) { //корзинка
            changeFragment(Const.BASKET_FRAGMENT, null);
        } else if (id == R.id.nav_desires) { //желания
            changeFragment(Const.WISH_FRAGMENT,null);
        } else if (id == R.id.nav_orders) { // мои заказы
            changeFragment(Const.ORDER_FRAGMENT, null);
        } else if (id == R.id.nav_individual) { // індивідуальний заказ
            changeFragment(Const.PAY_FRAGMENT, null);
        } else if (id == R.id.nav_consulting) { // подзвонити нам
            Uri call = Uri.parse("tel:" + Const.TEL_NUMBER);
            Intent surf = new Intent(Intent.ACTION_DIAL, call);
            startActivity(surf);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
