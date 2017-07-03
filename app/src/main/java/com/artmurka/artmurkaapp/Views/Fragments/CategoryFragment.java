package com.artmurka.artmurkaapp.Views.Fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.artmurka.artmurkaapp.Model.Retrofit.Success;

import com.artmurka.artmurkaapp.Presenter.Adapters.RVcategoryAdapter;
import com.artmurka.artmurkaapp.Presenter.ICategoryPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Presenter.*;

import java.util.ArrayList;


public class CategoryFragment extends Fragment implements ICategoryFragment {


    ICategoryPresenter presenter;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerAdapter;

    public CategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        setRetainInstance(true);
        if (presenter == null) {
            presenter = new CategoryPresenter(this);
        }

        presenter.getCategoriesData(true);

        return inflater.inflate(R.layout.fragment_category, container, false);
    }


    @Override
    public void showCategories(ArrayList<Success> categoriesList) {
        if (recyclerView == null) {
            recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        }
        RecyclerView.LayoutManager recyclerLayoutManager = new GridLayoutManager(getView().getContext(), 2);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerAdapter = new RVcategoryAdapter(getView().getContext(), categoriesList);

        recyclerView.setAdapter(recyclerAdapter);
    }


    @Override
    public void showError(String error) {
    }
}
