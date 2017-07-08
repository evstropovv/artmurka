package com.artmurka.artmurkaapp.Views.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.GoodsProperties;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Success;
import com.artmurka.artmurkaapp.Presenter.Adapters.ItemListPresenter;
import com.artmurka.artmurkaapp.Presenter.Adapters.RVcategoryAdapter;
import com.artmurka.artmurkaapp.Presenter.Adapters.RVitemListAdapter;
import com.artmurka.artmurkaapp.Presenter.ICategoryPresenter;
import com.artmurka.artmurkaapp.Presenter.IPresenterItemList;
import com.artmurka.artmurkaapp.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;


public class ItemListFragment extends Fragment implements IItemListFragment {

    private RecyclerView recyclerView;
    private RVitemListAdapter recyclerAdapter;
    private String url = "vushivki";
    private IPresenterItemList presenter;

    public ItemListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_item_list, container, false);

        Bundle bundle = getArguments();
        if (bundle!=null){
            if (bundle.getString("url")!=null){
                url = bundle.getString("url");
            }
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager recyclerLayoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerAdapter = new RVitemListAdapter(view.getContext());
        recyclerView.setAdapter(recyclerAdapter);

        if (presenter ==null){
            presenter = new ItemListPresenter(this, url);
        }
        presenter.getCategoriesData();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showItemList(ArrayList<GoodsProperties> goodsProperties) {
        recyclerAdapter.setData(goodsProperties);
    }

    @Override
    public void showError(String error) {
    }
}
