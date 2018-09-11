package com.artmurka.artmurkaapp.views.fragments;


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

import com.artmurka.artmurkaapp.model.databases.Preferences;
import com.artmurka.artmurkaapp.model.pojo.itemlist.itemlist.GoodsProperties;
import com.artmurka.artmurkaapp.presenter.adapters.RVitemListAdapterList;
import com.artmurka.artmurkaapp.presenter.adapters.RVitemListGridAdapter;
import com.artmurka.artmurkaapp.presenter.ItemListPresenter;
import com.artmurka.artmurkaapp.presenter.adapters.RVitemListAdapter;
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IPresenterItemList;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.views.fragments.interfaces.IItemListFragment;

import java.util.ArrayList;


public class ItemListFragment extends Fragment implements IItemListFragment {

    private RecyclerView recyclerView;
    private RVitemListAdapter recyclerAdapter;
    private RVitemListAdapterList recyclerAdapterList;
    private RVitemListGridAdapter recyclerGridAdapter;
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
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar2);
        Log.d("Log.d", "itemListFragment ");
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.getString("url") != null) {
                url = bundle.getString("url");
            }
            if (bundle.getString("sort") != null) {
                sort = bundle.getString("sort");
            }
            if (bundle.getString("order") != null) {
                order = bundle.getString("order");
            }
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        if (Preferences.getListSettings() == 1) {

            RecyclerView.LayoutManager recyclerLayoutManager = new GridLayoutManager(view.getContext(), 2);
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

        } else if (Preferences.getListSettings() == 2) {
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
        } else if (Preferences.getListSettings() == 3) {

            StaggeredGridLayoutManager recyclerLayoutManager = new StaggeredGridLayoutManager(3,
                    StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(recyclerLayoutManager);
            recyclerGridAdapter = new RVitemListGridAdapter(view.getContext());
            recyclerView.setAdapter(recyclerGridAdapter);

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
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDetach(); // delete link for this fragment in presenter
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
        switch (Preferences.getListSettings()) {
            case 1:
                recyclerAdapter.setData(goodsProperties);
                break;
            case 2:
                recyclerAdapterList.setData(goodsProperties);
                break;
            case 3:
                recyclerGridAdapter.setData(goodsProperties);
                break;
            default:
                break;
        }
        progressBar.setVisibility(View.INVISIBLE);
        isLoading = false;
    }

    @Override
    public void showError(String error) {
        Snackbar.make(getView(), error, Snackbar.LENGTH_LONG)
                .setAction(getResources().getString(R.string.any_error), new View.OnClickListener() {
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
