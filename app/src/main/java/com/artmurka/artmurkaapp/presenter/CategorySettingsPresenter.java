package com.artmurka.artmurkaapp.presenter;

import com.artmurka.artmurkaapp.presenter.interfaces_presenter.ICategorySettingsPresenter;
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ICategorySettings;

import java.util.HashMap;

public class CategorySettingsPresenter implements ICategorySettingsPresenter {
    ICategorySettings fragment;
    public CategorySettingsPresenter(ICategorySettings fragment){
        this.fragment = fragment;
    }


    @Override
    public void applyChanges(HashMap<String, String> settings, int sort) {

    }
}
