package com.artmurka.artmurkaapp.Views.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    RVcategoryAdapter recyclerAdapter;

    public CategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager recyclerLayoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerAdapter = new RVcategoryAdapter(view.getContext());
        recyclerView.setAdapter(recyclerAdapter);
        if (presenter == null) {
            presenter = new CategoryPresenter(this);
        }
        presenter.getCategoriesData(true);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void showCategories(ArrayList<Success> categoriesList) {
        recyclerAdapter.setData(categoriesList);
    }

    @Override
    public void showError(String error) {

    }
}
