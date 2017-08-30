package com.artmurka.artmurkaapp.Presenter;

import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.ICategorySettingsPresenter;
import com.artmurka.artmurkaapp.Views.Activities.IMainActivity;
import com.artmurka.artmurkaapp.Views.Activities.MainActivity;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.ICategorySettings;

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
