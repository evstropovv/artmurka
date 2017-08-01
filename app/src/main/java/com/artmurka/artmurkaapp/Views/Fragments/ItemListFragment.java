package com.artmurka.artmurkaapp.Views.Fragments;


import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Toast;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.GoodsProperties;
import com.artmurka.artmurkaapp.Presenter.ItemListPresenter;
import com.artmurka.artmurkaapp.Presenter.Adapters.RVitemListAdapter;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.IPresenterItemList;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.IItemListFragment;

import java.util.ArrayList;


public class ItemListFragment extends Fragment implements IItemListFragment {

    private RecyclerView recyclerView;
    private RVitemListAdapter recyclerAdapter;
    private String url = "vushivki";
    private IPresenterItemList presenter;
    private int curPage = 1;

    public ItemListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.getString("url") != null) {
                url = bundle.getString("url");
            }
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        final RecyclerView.LayoutManager recyclerLayoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerAdapter = new RVitemListAdapter(view.getContext());
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = recyclerLayoutManager.getChildCount();//смотрим сколько элементов на экране
                int totalItemCount = recyclerLayoutManager.getItemCount();//сколько всего элементов

                Toast.makeText(getContext(), "vItemCount=" + visibleItemCount + " totalItemCount=" + totalItemCount, Toast.LENGTH_SHORT).show();
                if (totalItemCount - visibleItemCount < 8) {
                    presenter.getCategoriesData(++curPage);
                }
            }
        });


        if (presenter == null) {
            presenter = new ItemListPresenter(this, url);
        }
        presenter.getCategoriesData(curPage);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        Snackbar.make(getView(), error, Snackbar.LENGTH_LONG)
                .setAction("Угу...", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }}).show();
    }
}
