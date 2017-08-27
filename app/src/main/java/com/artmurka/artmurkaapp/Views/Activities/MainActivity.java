package com.artmurka.artmurkaapp.Views.Activities;

import android.content.DialogInterface;
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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.artmurka.artmurkaapp.BuildConfig;
import com.artmurka.artmurkaapp.Model.Databases.Preferences;
import com.artmurka.artmurkaapp.Other.PayLiq;
import com.artmurka.artmurkaapp.Views.Fragments.BasketFragment;
import com.artmurka.artmurkaapp.Other.Const;
import com.artmurka.artmurkaapp.Views.Fragments.ItemListFragment;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Fragments.CategoryFragment;
import com.artmurka.artmurkaapp.Views.Fragments.OrderFragment;
import com.artmurka.artmurkaapp.Views.Fragments.WishFragment;


public class MainActivity extends AppCompatActivity implements IMainActivity, NavigationView.OnNavigationItemSelectedListener {
    private TextView tvBigName, tvSmallName;
    CategoryFragment fragCategory;
    private final String TAG = "Storage_category_fragment";
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Preferences.init(this);
        loadShopFragment();
        setUI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                tvBigName.setText(data.getExtras().getString("name"));
                tvSmallName.setText(data.getExtras().getString("email"));
                btnLogin.setText("Вийти");
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
        tvSmallName = (TextView) header.findViewById(R.id.tvSmallName);
        tvSmallName.setText(Preferences.getEmail());
        tvBigName = (TextView) header.findViewById(R.id.tvBigName);
        tvBigName.setText(Preferences.getName());
        btnLogin = (Button) header.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Preferences.getIsLogin()){
                    btnLogin.setText("Увійти");
                    Intent intent = new Intent(v.getContext(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage(Preferences.getName()+", Ви впевнені що хочете вийти з акаунта?")
                            .setPositiveButton("Так", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Preferences.setConsumerKey(BuildConfig.CONSUMER_KEY);
                                    Preferences.setConsumerSecret(BuildConfig.CONSUMER_SECRET);
                                    Preferences.setOauthToken(BuildConfig.OAUTH_TOKEN);
                                    Preferences.setOauthTokenSecret(BuildConfig.OAUTH_TOKEN_SECRET);
                                    tvBigName.setText("Арт-Мурка");
                                    tvSmallName.setText("artmurka.com");
                                    Preferences.setIsLogin(false);
                                }
                            })
                            .setNegativeButton("Ні", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                    builder.create().show();
                }
            }
        });

        //логин фейсбук
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (fragCategory != null) {
            outState.putString(TAG, fragCategory.getTag());
        }
    }

    @Override
    public void changeFragment(int fragment, String url) {
        FragmentManager fm = getSupportFragmentManager();
        switch (fragment) {
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
                new PayLiq(getBaseContext(), null).start();
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
            changeFragment(Const.WISH_FRAGMENT, null);
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
