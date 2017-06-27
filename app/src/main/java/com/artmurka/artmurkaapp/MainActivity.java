package com.artmurka.artmurkaapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.design.widget.TabLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.artmurka.artmurkaapp.Activities.PrefActivity;
import com.artmurka.artmurkaapp.Activities.SelectedGood;
import com.artmurka.artmurkaapp.Fragments.CategoryFragment;
import com.artmurka.artmurkaapp.Fragments.SplashFragment;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentTransaction;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentTransaction = getFragmentManager();
        loadSplashScreen();
        setUI();
    }

    private void loadSplashScreen() {
        SplashFragment splashFragment = new SplashFragment();
        fragmentTransaction.beginTransaction().replace(R.id.content_frame, splashFragment)
                .addToBackStack(null)
                .commit();

    }

    private void loadShopFragment() {
        CategoryFragment fragCategory = new CategoryFragment();
        fragmentTransaction.beginTransaction().replace(R.id.mainFrame, fragCategory)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(0,1,0, "Настройки");
        mi.setIntent(new Intent(this, PrefActivity.class));
        return super.onCreateOptionsMenu(menu);
    }
    private void setUI(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar !=null){
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
            setSupportActionBar(toolbar);
        }
        btn = (Button)findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), SelectedGood.class);
                startActivity(intent);
            }
        });
    }


}
