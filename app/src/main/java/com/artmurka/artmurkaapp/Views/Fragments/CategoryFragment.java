package com.artmurka.artmurkaapp.Views.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.artmurka.artmurkaapp.Model.Retrofit.Success;

import com.artmurka.artmurkaapp.Other.Const;
import com.artmurka.artmurkaapp.Presenter.Adapters.RVcategoryAdapter;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.ICategoryPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Presenter.*;
import com.artmurka.artmurkaapp.Views.Activities.MainActivity;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.ICategoryFragment;

import java.util.ArrayList;


public class CategoryFragment extends Fragment implements ICategoryFragment {


    ICategoryPresenter presenter;
    RecyclerView recyclerView;
    RVcategoryAdapter recyclerAdapter;
    Button btnCall;

    public CategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
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
        RecyclerView.LayoutManager recyclerLayoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerAdapter = new RVcategoryAdapter(view.getContext());
        recyclerView.setAdapter(recyclerAdapter);
        if (presenter == null) {
            presenter = new CategoryPresenter(this);
        }
        if (!isOnline()) {
            showError("Відсутній інтернет. Перезавантажити ?", view);
        } else {
            presenter.getCategoriesData(true);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

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
    public void showCategories(ArrayList<Success> categoriesList) {
        recyclerAdapter.setData(categoriesList);
    }

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
    }

    private void showError(String error, View view) {
        Snackbar.make(view, error, Snackbar.LENGTH_INDEFINITE)
                .setAction("Так", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getContext(), MainActivity.class));
                        getActivity().finish();
                    }
                }).show();
    }
}
