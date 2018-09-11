package com.artmurka.artmurkaapp.views.fragments;


import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.artmurka.artmurkaapp.model.databases.Preferences;
import com.artmurka.artmurkaapp.model.pojo.itemlist.checkout.OrderDesc;
import com.artmurka.artmurkaapp.model.pojo.itemlist.novaposhta.Areas.AreasResponse;
import com.artmurka.artmurkaapp.presenter.adapters.RVcheckoutAdapter;
import com.artmurka.artmurkaapp.presenter.CheckoutPresenter;
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.ICheckoutPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.views.fragments.interfaces.ICheckoutFragment;

import java.util.ArrayList;
import java.util.List;

public class CheckoutFragment extends Fragment implements ICheckoutFragment {

    private ICheckoutPresenter presenter;
    private TextInputLayout emailLayout;
    private RecyclerView recyclerView;
    private RVcheckoutAdapter adapter;
    private TextView tvSumPrice;
    private Button btnPostCheckout;
    private AutoCompleteTextView etMsg;
    private EditText etPhone, etEmail;
    private Spinner spinnerDelivery, spinnerPayment;
    private ArrayList<String> deliveryList;
    private ArrayList<String> paymentList;
    private BottomSheetBehavior bottomSheetBehavior;
    private LinearLayout llBottomSheet;
    private ArrayAdapter<String> cityAdapter;
    public CheckoutFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        deliveryList = new ArrayList<>();
        paymentList = new ArrayList<>();
        setUI(view);
        if (presenter == null) presenter = new CheckoutPresenter(this);
        presenter.getData();

        return view;
    }

    private void setUI(View view) {
        spinnerDelivery = (Spinner) view.findViewById(R.id.spinnerDelivery);
        spinnerPayment = (Spinner) view.findViewById(R.id.spinnerPayment);

        llBottomSheet = (LinearLayout) view.findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);

        tvSumPrice = (TextView) view.findViewById(R.id.tvSumPrice);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvCheckout);
        final RecyclerView.LayoutManager recyclerLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        adapter = new RVcheckoutAdapter(view.getContext(), this);
        recyclerView.setAdapter(adapter);
        btnPostCheckout = (Button) view.findViewById(R.id.btnPostCheckout);


        etMsg = (AutoCompleteTextView) view.findViewById(R.id.etMsg);

        etMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                etMsg.setError(null);
                if (charSequence.toString().toCharArray().length>2){
                    presenter.cityChanged(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etPhone = (EditText) view.findViewById(R.id.etPhone);

        etPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        etPhone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                etEmail.setError(null);
                return false;
            }
        });
        emailLayout = (TextInputLayout) view.findViewById(R.id.emailLayout);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                etEmail.setError(null);
                return false;
            }
        });
        etEmail.setText(Preferences.getEmail().matches("artmurka.com") ? "" : Preferences.getEmail());
        btnPostCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean post = true;

                //check for valid email
                if (!presenter.isEmailValid(etEmail.getText().toString())){
                    etEmail.setError(getResources().getString(R.string.email_error));
                    post = false;
                }
                //check for valid phone
                if (!presenter.isValidPhone(etPhone.getText().toString())){
                    etPhone.setError(getResources().getString(R.string.phone_error));
                    post = false;
                }
                //check for length Msg with city
                if (etMsg.getText().toString().toCharArray().length<4){
                    etMsg.setError(getResources().getString(R.string.city_error));
                }

                if (!post) { //if any check if false = nothing
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    int payPos = spinnerPayment.getSelectedItemPosition() + 1;
                    int delPos = spinnerDelivery.getSelectedItemPosition() + 1;
                    presenter.postCheckout(etPhone.getText().toString(), etMsg.getText().toString(), etEmail.getText().toString(), String.valueOf(payPos), String.valueOf(delPos));
                }
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
        Toast.makeText(getContext().getApplicationContext(), msg, Toast.LENGTH_LONG).show();
//        Toast.makeText(getContext().getApplicationContext(), "Заказ успішно сформовано", Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(getContext(), MainActivity.class);
//        startActivity(intent);
    }

    @Override
    public void setDataSpinner(ArrayList<String> deliveryList, ArrayList<String> paymentList) {
        this.deliveryList = deliveryList;
        this.paymentList = paymentList;
        ArrayAdapter<String> spinnerDeliveryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, deliveryList);
        spinnerDeliveryAdapter.setDropDownViewResource(R.layout.spinner_item);

        ArrayAdapter<String> spinnerPaymentAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, paymentList);
        spinnerPaymentAdapter.setDropDownViewResource(R.layout.spinner_item);

        spinnerDelivery.setAdapter(spinnerDeliveryAdapter);
        spinnerPayment.setAdapter(spinnerPaymentAdapter);


    }

    @Override
    public void setSityes(String[] sityes) {
        cityAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1);
        etMsg.setAdapter(cityAdapter);
        cityAdapter.addAll(sityes);
        cityAdapter.setNotifyOnChange(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Оформлення заказу");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAreas(AreasResponse areas) {

    }

    @Override
    public void setCities(List<String> cities) {

    }

    @Override
    public void setWarehouses(List<String> warehouses) {

    }

    @Override
    public void showDialog(String msg) {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(getContext(), "Home pressed", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


}
