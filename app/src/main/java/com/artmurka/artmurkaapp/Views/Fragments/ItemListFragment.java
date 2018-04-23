package com.artmurka.artmurkaapp.Views.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.artmurka.artmurkaapp.Model.Databases.Preferences;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemList.GoodsProperties;
import com.artmurka.artmurkaapp.Presenter.Adapters.RVitemListAdapterList;
import com.artmurka.artmurkaapp.Presenter.ItemListPresenter;
import com.artmurka.artmurkaapp.Presenter.Adapters.RVitemListAdapter;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.IPresenterItemList;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.IItemListFragment;

import java.util.ArrayList;


public class ItemListFragment extends Fragment implements IItemListFragment {

    private RecyclerView recyclerView;
    private RVitemListAdapter recyclerAdapter;
    private RVitemListAdapterList recyclerAdapterList;
    private String url = "";
    private IPresenterItemList presenter;
    private int curPage = 1;
    private String sort = "name";
    private String order = "asc";
    private int visibleThreshold = 4;
    private Boolean isLoading = false;
    private int totalItemCount, lastVisibleItem;
    private ProgressBar progressBar;

    public ItemListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar2);
        Log.d("Log.d", "itemListFragment ");
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.getString("url") != null) {
                url = bundle.getString("url");
            }
            if (bundle.getString("sort")!=null){
                sort = bundle.getString("sort");
            }
            if (bundle.getString("order")!=null){
                order= bundle.getString("order");
            }
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        if (Preferences.getListSettings()==1){

       //     final RecyclerView.LayoutManager recyclerLayoutManager = new GridLayoutManager(view.getContext(), 2);
            StaggeredGridLayoutManager recyclerLayoutManager =new StaggeredGridLayoutManager(2,
                    StaggeredGridLayoutManager.VERTICAL);
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
                    presenter.getCategoriesData(++curPage);
                }
            });

        } else {
            final LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(view.getContext());
            recyclerLayoutManager.setOrientation(LinearLayout.VERTICAL);
            recyclerView.setLayoutManager(recyclerLayoutManager);
            recyclerAdapterList = new RVitemListAdapterList(view.getContext());
            recyclerView.setAdapter(recyclerAdapterList);

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalItemCount = recyclerLayoutManager.getItemCount();
                    Log.d("Log.d", totalItemCount + "");
                    lastVisibleItem = ((LinearLayoutManager) recyclerLayoutManager).findLastVisibleItemPosition();
                    Log.d("Log.d", lastVisibleItem + "");
                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        presenter.getCategoriesData(++curPage);
                        progressBar.setVisibility(View.VISIBLE);
                        isLoading = true;
                    }
                }
            });
        }



        if (presenter == null) {
            presenter = new ItemListPresenter(this, url, sort, order);
            Preferences.setListUrl(url);
        }
        presenter.getCategoriesData(curPage);
        progressBar.setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); //for menu
    }


    @Override
    public void showItemList(ArrayList<GoodsProperties> goodsProperties) {
        if (Preferences.getListSettings()==1){
            recyclerAdapter.setData(goodsProperties);
        } else {
            recyclerAdapterList.setData(goodsProperties);
        }
        progressBar.setVisibility(View.INVISIBLE);
        isLoading = false;
    }

    @Override
    public void showError(String error) {
        Snackbar.make(getView(), error, Snackbar.LENGTH_LONG)
                .setAction("Якась помилка...", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setTitle(String title) {
        try {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stopProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.sort).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }
}
