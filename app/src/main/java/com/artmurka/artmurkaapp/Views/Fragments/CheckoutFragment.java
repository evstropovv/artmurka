package com.artmurka.artmurkaapp.Views.Fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.OrderDesc;
import com.artmurka.artmurkaapp.Presenter.Adapters.RVcheckoutAdapter;
import com.artmurka.artmurkaapp.Presenter.CheckoutPresenter;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.ICheckoutPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.ICheckoutFragment;

import java.util.ArrayList;
import java.util.List;

public class CheckoutFragment extends Fragment implements ICheckoutFragment {
    ICheckoutPresenter presenter;
    private RecyclerView recyclerView;
    private RVcheckoutAdapter adapter;
    private TextView tvSumPrice;
    private Button btnPostCheckout;
    private EditText etPhone, etMsg, etEmail;
    private Spinner spinnerDelivery, spinnerPayment;
    ArrayList<String> deliveryList;
    ArrayList<String> paymentList;
    public CheckoutFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        setUI(view);
        deliveryList = new ArrayList<>();
        paymentList = new ArrayList<>();

        if (presenter == null) presenter = new CheckoutPresenter(this);
        presenter.getData();

        return view;
    }

    private void setUI(View view) {
        spinnerDelivery = (Spinner)view.findViewById(R.id.spinnerDelivery);
        spinnerPayment = (Spinner)view.findViewById(R.id.spinnerPayment);

        tvSumPrice = (TextView)view.findViewById(R.id.tvSumPrice);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvCheckout);
        final RecyclerView.LayoutManager recyclerLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        adapter = new RVcheckoutAdapter(view.getContext(), this);
        recyclerView.setAdapter(adapter);
        btnPostCheckout = (Button)view.findViewById(R.id.btnPostCheckout);
        etMsg = (EditText) view.findViewById(R.id.etMsg);
        etPhone = (EditText)view.findViewById(R.id.etPhone);
        etEmail = (EditText)view.findViewById(R.id.etEmail);

        btnPostCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int payPos = spinnerPayment.getSelectedItemPosition()+1;
                int delPos = spinnerDelivery.getSelectedItemPosition()+1;
                Log.d("Log.d","payment select: " + payPos);
                Log.d("Log.d","delivery select: " + delPos);
                presenter.postCheckout(etPhone.getText().toString(), etMsg.getText().toString(), etEmail.getText().toString(), String.valueOf(payPos), String.valueOf(delPos));
            }
        });
    }

    @Override
    public void showCheckout(List<OrderDesc> list) {
        adapter.setData(list);
    }

    @Override
    public void refreshSumPrice(String price) {
        tvSumPrice.setText(price);
    }

    @Override
    public void showOrderIsProcessed(String msg) {
        Snackbar.make(getView(), msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setDataSpinner(ArrayList<String> deliveryList, ArrayList<String> paymentList) {
        this.deliveryList = deliveryList;
        this.paymentList = paymentList;
        ArrayAdapter<String> spinnerDeliveryAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, deliveryList);
        spinnerDeliveryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> spinnerPaymentAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, paymentList);
        spinnerPaymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerDelivery.setAdapter(spinnerDeliveryAdapter);
        spinnerPayment.setAdapter(spinnerPaymentAdapter);

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(getContext(),"asdf", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
