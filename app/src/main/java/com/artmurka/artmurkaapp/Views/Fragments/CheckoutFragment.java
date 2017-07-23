package com.artmurka.artmurkaapp.Views.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.OrderDesc;
import com.artmurka.artmurkaapp.Presenter.Adapters.RVcheckoutAdapter;
import com.artmurka.artmurkaapp.Presenter.CheckoutPresenter;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.ICheckoutPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.ICheckoutFragment;
import com.google.gson.Gson;

import java.util.List;

public class CheckoutFragment extends Fragment implements ICheckoutFragment {
    ICheckoutPresenter presenter;
    private RecyclerView recyclerView;
    private RVcheckoutAdapter adapter;
    private TextView tvSumPrice;

    public CheckoutFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        setUI(view);

        if (presenter == null) presenter = new CheckoutPresenter(this);
        presenter.getData();

        return view;
    }

    private void setUI(View view) {
        tvSumPrice = (TextView)view.findViewById(R.id.tvSumPrice);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvCheckout);
        final RecyclerView.LayoutManager recyclerLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        adapter = new RVcheckoutAdapter(view.getContext(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showCheckout(List<OrderDesc> list) {
        Log.d("Log.d", "checkout answer " + list);
        adapter.setData(list);
    }

    @Override
    public void refreshSumPrice(String price) {
        tvSumPrice.setText(price);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(getContext(),"asdf", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
