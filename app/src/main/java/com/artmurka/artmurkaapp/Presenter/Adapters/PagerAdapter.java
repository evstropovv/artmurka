package com.artmurka.artmurkaapp.Presenter.Adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.artmurka.artmurkaapp.Views.Fragments.FragmentAboutGoods;
import com.artmurka.artmurkaapp.Views.Fragments.FragmentDescriptionGoods;


public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;
    private String currentId;
    private Bundle bundle;
    public PagerAdapter(FragmentManager fm, int numberOfTabs, String id) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        this.currentId = id;
        bundle = new Bundle();
        bundle.putString("id", currentId);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentAboutGoods fragmentAboutGoods = new FragmentAboutGoods();
                fragmentAboutGoods.setArguments(bundle);
                return fragmentAboutGoods;
            case 1:
                FragmentDescriptionGoods fragmentDescriptionGoods = new FragmentDescriptionGoods();
                fragmentDescriptionGoods.setArguments(bundle);
                return fragmentDescriptionGoods;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
