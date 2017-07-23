package com.artmurka.artmurkaapp.Views.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.artmurka.artmurkaapp.Presenter.Adapters.PagerAdapter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Fragments.CategoryFragment;
import com.artmurka.artmurkaapp.Views.Fragments.CheckoutFragment;

public class CheckoutActivity extends AppCompatActivity implements ICheckoutActivity {
    CheckoutFragment checkoutFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        loadShopFragment();
        setUI();

    }


    private void setUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            //toolbar.setTitleTextColor(getResources().getColor(R.color.white));
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void changeFragment(int currentFragment) {

    }


    private void loadShopFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag("CHECKOUT");
        if (fragment == null) {
            checkoutFragment = new CheckoutFragment();
            fm.beginTransaction()
                    .replace(R.id.pager, checkoutFragment, "CHECKOUT")
                    .commit();
            fm.executePendingTransactions();
        } else {
            checkoutFragment = (CheckoutFragment) fragment;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

