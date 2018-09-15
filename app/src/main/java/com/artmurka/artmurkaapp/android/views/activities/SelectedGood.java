package com.artmurka.artmurkaapp.android.views.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.artmurka.artmurkaapp.presenter.adapters.PagerAdapter;
import com.artmurka.artmurkaapp.R;

public class SelectedGood extends AppCompatActivity {

private String currentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_good);
        currentId = getIntent().getExtras().getString("id");
        setUI();
    }

    private void setUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        toolbar.setTitle("Категорія");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tabLayout.addTab(tabLayout.newTab().setText("Опис"));

        tabLayout.addTab(tabLayout.newTab().setText("Доставка-оплата"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter tabAdapter = new PagerAdapter(getFragmentManager(), tabLayout.getTabCount(), currentId);

        viewPager.setAdapter(tabAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
