package com.artmurka.artmurkaapp.android.views.fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artmurka.artmurkaapp.data.model.databases.Preferences;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.orders.Orders;
import com.artmurka.artmurkaapp.presenter.adapters.RVorderListAdapter;
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IOrderPresenter;
import com.artmurka.artmurkaapp.presenter.OrdersPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IOrderFragment;

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

        if (Preferences.INSTANCE.getIsLogin()){
            presenter.getOrders();
        }else {
            Snackbar snackbar = Snackbar.make(view, getResources().getString(R.string.fragment_order_status_toast_message), Snackbar.LENGTH_LONG)
                    .setAction("Action", null);
            snackbar.setDuration(8000); // 8 секунд
            snackbar.show();
        }

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
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.fragment_order_status_my_orders));
        }catch ( NullPointerException e){e.printStackTrace();}
    }
}
