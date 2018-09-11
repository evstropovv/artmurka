package com.artmurka.artmurkaapp.views.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IAboutGoodsPresenter;
import com.artmurka.artmurkaapp.R;


public class FragmentDescriptionGoods extends Fragment {

    IAboutGoodsPresenter presenter;

    public FragmentDescriptionGoods() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_description_goods, container, false);
        return view;
    }

}
