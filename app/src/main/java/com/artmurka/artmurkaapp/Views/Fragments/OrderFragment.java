package com.artmurka.artmurkaapp.Views.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Orders.Orders;
import com.artmurka.artmurkaapp.Presenter.Adapters.RVorderListAdapter;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.IOrderPresenter;
import com.artmurka.artmurkaapp.Presenter.OrdersPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.IOrderFragment;
import com.google.gson.Gson;

import java.util.List;


public class OrderFragment extends Fragment implements IOrderFragment {

    //фрагмент страницы заказов

    private IOrderPresenter presenter;
    private RecyclerView rvOrder;
    private RVorderListAdapter adapter;

    public OrderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        if (presenter ==null) presenter = new OrdersPresenter(this);
        rvOrder = (RecyclerView) view.findViewById(R.id.rvOrder);
        final RecyclerView.LayoutManager recyclerLayoutManager = new LinearLayoutManager(view.getContext());
        rvOrder.setLayoutManager(recyclerLayoutManager);
        adapter = new RVorderListAdapter(view.getContext());
        rvOrder.setAdapter(adapter);
        presenter.getOrders();
        return view;
    }

    @Override
    public void showOrders(Orders list) {
        adapter.setData(list);
    }

    @Override
    public void onResume() {
        super.onResume();
        try{
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Мої замовлення");
        }catch ( NullPointerException e){e.printStackTrace();}
    }
}
