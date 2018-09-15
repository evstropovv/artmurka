package com.artmurka.artmurkaapp.android.views.activities.main;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.artmurka.artmurkaapp.BuildConfig;
import com.artmurka.artmurkaapp.android.views.activities.login.LoginActivity;
import com.artmurka.artmurkaapp.android.views.fragments.FragmentCategoryChilds;
import com.artmurka.artmurkaapp.model.pojo.itemlist.categories.Success;
import com.artmurka.artmurkaapp.android.views.fragments.DeliveryFragment;
import com.artmurka.artmurkaapp.android.views.fragments.IndividualFragment;
import com.artmurka.artmurkaapp.model.databases.Preferences;
import com.artmurka.artmurkaapp.android.views.fragments.BasketFragment;
import com.artmurka.artmurkaapp.other.Const;
import com.artmurka.artmurkaapp.android.views.fragments.CategorySettings;
import com.artmurka.artmurkaapp.android.views.fragments.ItemListFragment;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.android.views.fragments.CategoryFragment;
import com.artmurka.artmurkaapp.android.views.fragments.OrderFragment;
import com.artmurka.artmurkaapp.android.views.fragments.WishFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;


public class MainActivity extends AppCompatActivity implements IMainActivity, NavigationView.OnNavigationItemSelectedListener, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;


    private TextView tvBigName, tvSmallName;
    private CategoryFragment fragCategory;
    private final String TAG = "Storage_category_fragment";
    private Button btnLogin;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Preferences.init(this);
        loadShopFragment();
        setUI();

        runtimePermission();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                tvBigName.setText(data.getExtras().getString("name"));
                tvSmallName.setText(data.getExtras().getString("email"));
                btnLogin.setText("?????");
                Preferences.setIsLogin(true);
            }
        }
    }

    private void loadShopFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(TAG);
        if (fragment == null) {
            fragCategory = new CategoryFragment();
            fm.beginTransaction()
                    .replace(R.id.mainFrame, fragCategory, TAG)
                    //without backstacktrace
                    .commitAllowingStateLoss();
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.card) {
            changeFragment(Const.BASKET_FRAGMENT, null, null, null);
        }
        if (item.getItemId() == R.id.sort) {
            changeFragment(Const.CATEGORY_SETTINGS_FRAGMENT, null, null, null);
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
        tvSmallName = (TextView) header.findViewById(R.id.tvSmallName);
        tvSmallName.setText(Preferences.getEmail());
        tvBigName = (TextView) header.findViewById(R.id.tvBigName);
        tvBigName.setText(Preferences.getName());
        btnLogin = (Button) header.findViewById(R.id.btnLogin);
        btnLogin.setText(Preferences.getIsLogin() ? "?????" : "??????");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Preferences.getIsLogin()) {
                    btnLogin.setText("??????");
                    Intent intent = new Intent(v.getContext(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage(Preferences.getName() + ", ?? ???????? ?? ?????? ????? ? ????????")
                            .setPositiveButton("???", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Preferences.setConsumerKey(BuildConfig.CONSUMER_KEY);
                                    Preferences.setConsumerSecret(BuildConfig.CONSUMER_SECRET);
                                    Preferences.setOauthToken(BuildConfig.OAUTH_TOKEN);
                                    Preferences.setOauthTokenSecret(BuildConfig.OAUTH_TOKEN_SECRET);
                                    Preferences.setName("???-?????");
                                    Preferences.setEmail("artmurka.com");
                                    tvBigName.setText("???-?????");
                                    tvSmallName.setText("artmurka.com");
                                    Preferences.setIsLogin(false);
                                }
                            })
                            .setNegativeButton("??", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                    builder.create().show();
                }
            }
        });

        //????? ???????
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
//        if (fragCategory != null) {
//            outState.putString(TAG, fragCategory.getTag());
//        }
    }

    @Override
    public void changeFragment(int fragment, String url, List<Success> childs, String catName) {
        FragmentManager fm = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        switch (fragment) {
            case 100:
                FragmentCategoryChilds categoryChilds = new FragmentCategoryChilds();

                bundle.putParcelableArrayList("childs", (ArrayList<? extends Parcelable>) childs);
                bundle.putString("catName", catName);
                categoryChilds.setArguments(bundle);

                fm.beginTransaction()
                        .replace(R.id.mainFrame, categoryChilds)
                        .addToBackStack("categoryChilds")
                        .commitAllowingStateLoss();
                fm.executePendingTransactions();
                break;

            case 101:
                CategoryFragment categoryFragment = new CategoryFragment();
                fm.beginTransaction()
                        .replace(R.id.mainFrame, categoryFragment)
                        .addToBackStack(TAG)
                        .commitAllowingStateLoss();
                fm.executePendingTransactions();
                break;

            case 102:
                ItemListFragment itemList = new ItemListFragment();
                bundle.putString("url", url);
                itemList.setArguments(bundle);
                fm.beginTransaction()
                        .replace(R.id.mainFrame, itemList)
                        .addToBackStack("a")
                        .commitAllowingStateLoss();
                fm.executePendingTransactions();
                break;
            case 103:
                BasketFragment cartFragment = new BasketFragment();
                fm.beginTransaction()
                        .replace(R.id.mainFrame, cartFragment)
                        .addToBackStack("cartFragment")
                        .commitAllowingStateLoss();
                fm.executePendingTransactions();
                break;
            case 104:
                WishFragment wishFragment = new WishFragment();
                fm.beginTransaction()
                        .replace(R.id.mainFrame, wishFragment)
                        .addToBackStack("wishFragment")
                        .commitAllowingStateLoss();
                fm.executePendingTransactions();
                break;
            case 105:
                OrderFragment orderFragment = new OrderFragment();
                fm.beginTransaction()
                        .replace(R.id.mainFrame, orderFragment)
                        .addToBackStack("orderFragment")
                        .commitAllowingStateLoss();
                fm.executePendingTransactions();
                break;
            case 106:
                CategorySettings categorySettingsFragment = new CategorySettings();
                fm.beginTransaction()
                        .replace(R.id.mainFrame, categorySettingsFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
                fm.executePendingTransactions();
                break;
            case 111:
                //induvidual ...
                IndividualFragment individualFragment = new IndividualFragment();
                fm.beginTransaction()
                        .replace(R.id.mainFrame, individualFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
                fm.executePendingTransactions();

                break;
            case 112:
                //delivery ...
                DeliveryFragment deliveryFragment = new DeliveryFragment();
                fm.beginTransaction()
                        .replace(R.id.mainFrame, deliveryFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
                fm.executePendingTransactions();
                break;
        }
    }

    private boolean runtimePermission() { //?????? ? ???????????? ??????????

        if (Build.VERSION.SDK_INT >= 23
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.INTERNET,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            Manifest.permission.ACCESS_WIFI_STATE,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.CALL_PHONE
                    },
                    100);
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) { //???????? ?????? ?? ??????????
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[3] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[4] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[5] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[6] == PackageManager.PERMISSION_GRANTED) {

                loadShopFragment();
                setUI();
            } else { //?????????? ????, ???? ?? ??????? ??????????.
                runtimePermission();
            }
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_catalog) { //???????
            changeFragment(Const.CATEGORY_FRAGMENT, null, null, null);
            // Handle the camera action
        } else if (id == R.id.nav_basket) { //????????
            changeFragment(Const.BASKET_FRAGMENT, null, null, null);
        } else if (id == R.id.nav_desires) { //???????
            changeFragment(Const.WISH_FRAGMENT, null, null, null);
        } else if (id == R.id.nav_orders) { // ??? ??????
            changeFragment(Const.ORDER_FRAGMENT, null, null, null);
        } else if (id == R.id.nav_individual) { // ?????????????? ?????, ????? - ??????? ?????? ?? 1 ???
            changeFragment(Const.PAY_FRAGMENT, null, null, null);
        } else if (id == R.id.nav_consulting) { // ?????????? ???
            Uri call = Uri.parse("tel:" + Const.TEL_NUMBER);
            Intent surf = new Intent(Intent.ACTION_DIAL, call);
            startActivity(surf);
        } else if (id == R.id.delivery) { // ?????????????? ?????, ????? - ??????? ?????? ?? 1 ???
            changeFragment(Const.DELIVERY, null, null, null);
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
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
            } else if (!doubleBackToExitPressedOnce) {
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "????????? ?? ??? ??? ??????", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            } else {
                super.onBackPressed();
                return;
            }
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
