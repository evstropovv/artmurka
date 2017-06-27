package com.artmurka.artmurkaapp.Adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.artmurka.artmurkaapp.Fragments.FragmentAboutGoods;
import com.artmurka.artmurkaapp.Fragments.FragmentDescriptionGoods;


public class PagerAdapter extends FragmentStatePagerAdapter {

    int numberOfTabs;

    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentAboutGoods();
            case 1:
                return new FragmentDescriptionGoods();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 0;
    }
}
