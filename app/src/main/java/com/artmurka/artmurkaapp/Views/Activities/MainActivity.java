package com.artmurka.artmurkaapp.Views.Activities;

import android.content.Intent;
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
import android.view.Menu;
import android.view.MenuItem;

import com.artmurka.artmurkaapp.Views.Fragments.BasketFragment;
import com.artmurka.artmurkaapp.Other.Const;
import com.artmurka.artmurkaapp.Views.Fragments.ItemListFragment;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Fragments.CategoryFragment;

public class MainActivity extends AppCompatActivity implements IMainActivity, NavigationView.OnNavigationItemSelectedListener{

    CategoryFragment fragCategory;
    private final String TAG = "Storage_category_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadShopFragment();
        setUI();

    }


    private void loadShopFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(TAG);
        if (fragment == null) {
            fragCategory = new CategoryFragment();
            fm.beginTransaction()
                    .replace(R.id.mainFrame, fragCategory, TAG)
                    .addToBackStack(TAG)
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

        } else if (id == R.id.nav_orders) { // мои заказы

        } else if (id == R.id.nav_settings) { //настройки
            Intent intent = new Intent(this, PrefActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_individual) { // індивідуальний заказ

        } else if (id == R.id.nav_consulting) { // подзвонити нам
            String number = "7777777777";
            Uri call = Uri.parse("tel:" + number);
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
