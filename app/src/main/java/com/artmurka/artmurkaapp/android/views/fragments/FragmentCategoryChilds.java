package com.artmurka.artmurkaapp.android.views.fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.artmurka.artmurkaapp.model.pojo.itemlist.categories.Success;
import com.artmurka.artmurkaapp.other.SaveDataFragment;
import com.artmurka.artmurkaapp.presenter.adapters.RVcategoryAdapter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ICategoryFragment;
import com.google.gson.Gson;

import java.util.List;


public class FragmentCategoryChilds extends Fragment implements ICategoryFragment {

    private RecyclerView recyclerView;
    private RVcategoryAdapter recyclerAdapter;
    private SaveDataFragment dataFragment;
    private List<Success> childs;
    public FragmentCategoryChilds() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_category_childs, container, false);
        FragmentManager fm = getFragmentManager();
        dataFragment = (SaveDataFragment) fm.findFragmentByTag("data");

        if (dataFragment == null) {
            dataFragment = new SaveDataFragment();
            fm.beginTransaction()
                    .add(dataFragment, "data")
                    .commit();
        }
        Bundle bundle = getArguments();
        if (bundle.getParcelableArrayList("childs") != null) {
            childs = bundle.getParcelableArrayList("childs");
        }
        if (bundle.getString("catName")!=null){
            setToolBarName(bundle.getString("catName"));
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager recyclerLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerAdapter = new RVcategoryAdapter(view.getContext());
        recyclerView.setAdapter(recyclerAdapter);

        if (dataFragment.getChilds() != null) {
            showCategories(dataFragment.getChilds());
        } else {
            if (isOnline()) {
                showCategories(childs);
            } else {
                showError("Відсутній інтернет");
            }
        }
        return view;

    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void showCategories(List<Success> categoriesList) {
        dataFragment.setChilds(categoriesList);
        recyclerAdapter.setData(categoriesList);
        Log.d("Log.d", "showCategories " + new Gson().toJson(categoriesList));

    }

    @Override
    public void setToolBarName(String name) {
        try {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(name);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }
}

