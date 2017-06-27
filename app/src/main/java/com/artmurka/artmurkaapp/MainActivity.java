package com.artmurka.artmurkaapp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.artmurka.artmurkaapp.Activities.PrefActivity;
import com.artmurka.artmurkaapp.Fragments.ShopFragment;

public class MainActivity extends AppCompatActivity {
    FragmentTransaction fragmentTransaction;
    ShopFragment shopFragment;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void loadingInfo() {

    }

    private void refreshFragment(){

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
}
