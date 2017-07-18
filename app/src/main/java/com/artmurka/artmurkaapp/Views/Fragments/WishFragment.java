package com.artmurka.artmurkaapp.Views.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artmurka.artmurkaapp.Model.InterfacesModel.IWishList;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.WishList.GoodsListDescription;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.WishList.WishList;
import com.artmurka.artmurkaapp.Presenter.Adapters.RVbasketAdapter;
import com.artmurka.artmurkaapp.Presenter.Adapters.RVwishListAdapter;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.IWishPresenter;
import com.artmurka.artmurkaapp.Presenter.WishPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.IWishFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class WishFragment extends Fragment implements IWishFragment {

    private IWishPresenter presenter;
    private RecyclerView recyclerView;
    private RVwishListAdapter recyclerAdapter;


    public WishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wish, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rvWish);
        final RecyclerView.LayoutManager recyclerLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerAdapter = new RVwishListAdapter(view.getContext());
        recyclerView.setAdapter(recyclerAdapter);

        if (presenter ==null) presenter = new WishPresenter(this);
        presenter.getDataForWishList();
        return view;
    }


    @Override
    public void showWishList(List<GoodsListDescription> list) {
        recyclerAdapter.setData(list);
    }
}
