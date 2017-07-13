package com.artmurka.artmurkaapp.Views.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.IFragmentAboutGoods;


public class FragmentAboutGoods extends Fragment implements IFragmentAboutGoods, LoaderManager.LoaderCallbacks<String> {




    public FragmentAboutGoods() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_about_goods, container, false);

        return view;
    }

    @Override
    public void setPhoto(String url) {

    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public void setPrice(String price) {

    }

    @Override
    public void setFullDescription(String fullDescription) {

    }

    @Override
    public void setDataForRecyclerView() {

    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
