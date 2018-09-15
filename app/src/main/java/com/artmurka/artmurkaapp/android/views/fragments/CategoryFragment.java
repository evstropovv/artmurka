package com.artmurka.artmurkaapp.android.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.artmurka.artmurkaapp.model.pojo.itemlist.categories.Success;

import com.artmurka.artmurkaapp.other.Const;
import com.artmurka.artmurkaapp.other.SaveDataFragment;
import com.artmurka.artmurkaapp.presenter.adapters.RVcategoryAdapter;
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.ICategoryPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.presenter.*;
import com.artmurka.artmurkaapp.android.views.activities.MainActivity;
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ICategoryFragment;

import java.util.List;


public class CategoryFragment extends Fragment implements ICategoryFragment {

    private ICategoryPresenter presenter;
    private RecyclerView recyclerView;
    private RVcategoryAdapter recyclerAdapter;
    private Button btnCall;
    private SaveDataFragment dataFragment;
    private ProgressBar progressBar;

    public CategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        FragmentManager fm = getFragmentManager();
        dataFragment = (SaveDataFragment) fm.findFragmentByTag("data");
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar2);

        if (dataFragment == null) {
            dataFragment = new SaveDataFragment();
            fm.beginTransaction()
                    .add(dataFragment, "data")
                    .commit();
        }

        btnCall = (Button) view.findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri call = Uri.parse("tel:" + Const.TEL_NUMBER);
                Intent surf = new Intent(Intent.ACTION_DIAL, call);
                startActivity(surf);
            }
        });


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager recyclerLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerAdapter = new RVcategoryAdapter(view.getContext());
        recyclerView.setAdapter(recyclerAdapter);


        if (presenter == null) {
            presenter = new CategoryPresenter(this, getContext());
        }

        if (dataFragment.getCategories() != null) {
            showCategories(dataFragment.getCategories());

        } else {
            if (!isOnline()) {
                showError("Відсутній інтернет. Перезавантажити ?");
            } else {
                presenter.getCategoriesData(true);
                progressBar.setVisibility(View.VISIBLE);
            }
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Каталог товарів");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void showCategories(List<Success> categoriesList) {
        dataFragment.setCategories(categoriesList);
        recyclerAdapter.setData(categoriesList);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setToolBarName(String name) {}

    @Override
    public void showError(String error) {
        if (getView() != null) {
            Snackbar.make(getView(), error, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Так", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getContext(), MainActivity.class));
                            getActivity().finish();
                        }
                    }).show();
        }
        progressBar.setVisibility(View.INVISIBLE);
    }

}
