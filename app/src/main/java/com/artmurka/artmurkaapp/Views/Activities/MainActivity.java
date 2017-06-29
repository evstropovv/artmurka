package com.artmurka.artmurkaapp.Views.Activities;

import android.app.FragmentManager;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Fragments.CategoryFragment;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentTransaction = getFragmentManager();
        setUI();
        loadShopFragment();
    }


    private void loadShopFragment() {
        CategoryFragment fragCategory = new CategoryFragment();
        fragmentTransaction.beginTransaction().replace(R.id.mainFrame, fragCategory)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(0, 1, 0, "Настройки");
        mi.setIntent(new Intent(this, PrefActivity.class));
        return super.onCreateOptionsMenu(menu);
    }

    private void setUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {

            setSupportActionBar(toolbar);
        }
    }
}
