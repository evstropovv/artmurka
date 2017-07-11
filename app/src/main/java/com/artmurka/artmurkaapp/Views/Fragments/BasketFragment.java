package com.artmurka.artmurkaapp.Views.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.artmurka.artmurkaapp.Model.InterfacesModel.IBasket;
import com.artmurka.artmurkaapp.Model.Modules.BasketRequest;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemBasket.BasketItems;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemBasket.Item;
import com.artmurka.artmurkaapp.Other.Const;
import com.artmurka.artmurkaapp.Presenter.Adapters.RVbasketAdapter;
import com.artmurka.artmurkaapp.Presenter.Adapters.RVitemListAdapter;
import com.artmurka.artmurkaapp.Presenter.BasketPresenter;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.IBasketPresenter;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.IPresenterItemList;
import com.artmurka.artmurkaapp.Presenter.ItemListPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Activities.MainActivity;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.IBasketFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BasketFragment extends Fragment implements IBasketFragment {
    private Button btnToMain;
    private TextView tvMessage;

    private RecyclerView recyclerView;
    private RVbasketAdapter recyclerAdapter;
    private IBasketPresenter presenter;

    public BasketFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        btnToMain = (Button) view.findViewById(R.id.btnToMain);
        btnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack(); //нажатие кнопки назад
            }
        });
        tvMessage = (TextView)view.findViewById(R.id.tvCartMessage);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        final RecyclerView.LayoutManager recyclerLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerAdapter = new RVbasketAdapter(view.getContext());
        recyclerView.setAdapter(recyclerAdapter);

        if (presenter ==null){
            presenter = new BasketPresenter(this);
        }
        presenter.getDataForbasket();

        return view;
    }


    @Override
    public void showError(String error) {

    }

    @Override
    public void showItemsInBasket(List<Item> items) {
        recyclerAdapter.setData(items);

    }

    @Override
    public void makeMessageInvisible(boolean b) {

        if (b){
            btnToMain.setVisibility(View.INVISIBLE);
            tvMessage.setVisibility(View.INVISIBLE);
        }else {
            btnToMain.setVisibility(View.VISIBLE);
            tvMessage.setVisibility(View.VISIBLE);
        }
    }
}
