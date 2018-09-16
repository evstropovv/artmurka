package com.artmurka.artmurkaapp.android.views.activities.checkout;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.artmurka.artmurkaapp.android.views.fragments.FragmenZakaz;
import com.artmurka.artmurkaapp.R;

public class CheckoutActivity extends AppCompatActivity implements ICheckoutActivity {

    FragmenZakaz fragmenZakaz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
       // loadShopFragment();
        loadTestFragment();
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
    private void loadTestFragment(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag("ZAKAZ");
        if (fragment == null) {
            fragmenZakaz = new FragmenZakaz();
            fm.beginTransaction()
                    .replace(R.id.pager, fragmenZakaz, "ZAKAZ")
                    .commit();
            fm.executePendingTransactions();
        } else {
            fragmenZakaz = (FragmenZakaz) fragment;
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

