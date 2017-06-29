package com.artmurka.artmurkaapp.Views.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artmurka.artmurkaapp.Model.Retrofit.Success;
import com.artmurka.artmurkaapp.Presenter.Adapters.RVcategoryAdapter;
import com.artmurka.artmurkaapp.Presenter.ICategoryPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Presenter.*;

import java.util.List;


public class CategoryFragment extends Fragment implements ICategoryFragment{


    ICategoryPresenter presenter;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager recyclerLayoutManager;

    public CategoryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (presenter ==null){
            presenter = new CategoryPresenter(this);
        }
        presenter.getCategoriesData(true);

        return inflater.inflate(R.layout.fragment_category, container, false);
    }


    @Override
    public void showCategories(List<Success> categoriesList) {

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
        if (getResources().getConfiguration().orientation == 1) {
            recyclerLayoutManager = new GridLayoutManager(getActivity(), 2);
        } else  recyclerLayoutManager = new GridLayoutManager(getActivity(), 3);

        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerAdapter = new RVcategoryAdapter(getActivity(), categoriesList);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onGetCategoryClick() {

    }

    @Override
    public void showError(String error) {

    }
}
