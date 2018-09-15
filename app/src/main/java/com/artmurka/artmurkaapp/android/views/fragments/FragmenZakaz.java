package com.artmurka.artmurkaapp.android.views.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.artmurka.artmurkaapp.model.pojo.itemlist.checkout.OrderDesc;
import com.artmurka.artmurkaapp.model.pojo.itemlist.novaposhta.Areas.AreasResponse;
import com.artmurka.artmurkaapp.presenter.CheckoutPresenter;
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.ICheckoutPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.android.views.activities.MainActivity;
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ICheckoutFragment;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class FragmenZakaz extends Fragment implements ICheckoutFragment {

    LinearLayout linearNovaPoshta, linerPikup, linearPayReciever, linerLiqPay;
    private TextView tvChoseAdress, tvPikup;
    private AutoCompleteTextView autoCompleteWarehouse;
    private AutoCompleteTextView autoCompleteCities;
    private LinearLayout linearPib, linearPay;
    private boolean npCheck = false;
    private boolean liqPayCheck = false;
    private boolean wasPayChoised = false;
    private CardView cardCity;
    private ICheckoutPresenter checkoutPresenter;
    private Button btnPostCheckout;
    private EditText etPhone, etName, etLastName, etPatr;
    private Observable<CharSequence> obsCity;
    private Observable<CharSequence> obsWarehouse;
    private Observable<CharSequence> obsName;
    private Observable<CharSequence> obsLastName;
    private Observable<CharSequence> obsName2;
    private Observable<CharSequence> obsPhone;
    private Disposable checkoutButtonDisposable;

    private void loadRxBindings() {
        obsCity = RxTextView.textChanges(autoCompleteCities);
        obsWarehouse = RxTextView.textChanges(autoCompleteWarehouse);
        obsName = RxTextView.textChanges(etName);
        obsLastName = RxTextView.textChanges(etLastName);
        obsName2 = RxTextView.textChanges(etPatr);
        obsPhone = RxTextView.textChanges(etPhone);

        Observable.combineLatest(obsName, obsLastName, obsName2, obsPhone, obsCity, obsWarehouse,
                (name, lastname, obsName2, obsPhone, obsCity, obsWarehouse)
                        ->
                        ((name.length() > 1)
                                && (lastname.length() > 1)
                                && (obsName2.length() > 1)
                                && (obsPhone.length() > 1)

                                //if chosen delivery NP -> check city and warehouse length
                                && (!npCheck || ((obsCity.length() > 1) && (obsWarehouse.length() > 0)))
                                && wasPayChoised ))
                .subscribe(aBoolean -> btnPostCheckout.setEnabled(aBoolean));
    }

    public FragmenZakaz() {
        // Required empty public constructor
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_zakaz, container, false);
        // Inflate the layout for this fragment
        initUI(view);


        checkoutPresenter = new CheckoutPresenter(this);

        autoCompleteWarehouse = (AutoCompleteTextView) view.findViewById(R.id.autocompleteWarehouse);

        btnPostCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkoutPresenter.postCheckout(etPhone.getText().toString(),
                        etName.getText().toString() + " " + etLastName.getText().toString()
                                + autoCompleteCities.getText().toString() + autoCompleteWarehouse.getText().toString(),
                        "va.evstropov@gmail.com", (npCheck ? "2" : "1"), (liqPayCheck ? "2" : "1")
                );
            }
        });

        return view;
    }

    private void initUI(View view) {
        linearPib = (LinearLayout)view.findViewById(R.id.linearPib);
        linearPay = (LinearLayout)view.findViewById(R.id.linearPay);
        linearNovaPoshta = (LinearLayout) view.findViewById(R.id.linearNovaPoshta);
        linerPikup = (LinearLayout) view.findViewById(R.id.linerPikup);
        tvChoseAdress = (TextView) view.findViewById(R.id.tvChoseAdress);
        tvPikup = (TextView) view.findViewById(R.id.tvPikup);
        cardCity = (CardView) view.findViewById(R.id.cardCity);
        linearPayReciever = (LinearLayout) view.findViewById(R.id.linearPayReciever);
        linerLiqPay = (LinearLayout) view.findViewById(R.id.linerLiqPay);
        btnPostCheckout = (Button) view.findViewById(R.id.btnZakaz);
        etPhone = (EditText) view.findViewById(R.id.etPhone);
        etName = (EditText) view.findViewById(R.id.etName);
        etLastName = (EditText) view.findViewById(R.id.etLastName);
        etPatr = (EditText) view.findViewById(R.id.etPatronymic);
        //   spinnerRegion = (SearchableSpinner) view.findViewById(R.id.spinnerRegion);
        autoCompleteCities = (AutoCompleteTextView) view.findViewById(R.id.spinnerCity);
        autoCompleteCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                checkoutPresenter.selectCity(i);
            }
        });
        autoCompleteCities.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String newText = charSequence.toString();
                Log.d("Log.d", "" + newText.length());
                if (newText.length() > 1) {
                    checkoutPresenter.getCities(newText);
                }
                if (newText.length() == 0) {
                    Log.d("Log.d", "text.length() == 0 " + newText.length());
                    getActivity().runOnUiThread(() -> setSpinnerCityChecked(false));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void setSpinnerCityChecked(Boolean isChecked) {

    }


    @Override
    public void onResume() {
        super.onResume();
        loadRxBindings();
        linearNovaPoshta.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                npCheck = false;
                setCheckNP(npCheck);
            }
        });
        linerPikup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                npCheck = true;
                setCheckNP(npCheck);
            }
        });
        linerLiqPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liqPayCheck = true;
                setLiqPaqCheck(liqPayCheck);
                wasPayChoised = true;
            }
        });
        linearPayReciever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liqPayCheck = false;
                setLiqPaqCheck(liqPayCheck);
                wasPayChoised = true;
            }
        });

    }


    void setCheckNP(boolean check) {
        if (!check) {
            linearNovaPoshta.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style_check));
            linerPikup.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style));
            tvChoseAdress.setVisibility(View.VISIBLE);
            tvPikup.setVisibility(View.GONE);
            cardCity.setVisibility(View.VISIBLE);

        } else {
            linearNovaPoshta.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style));
            linerPikup.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style_check));
            tvChoseAdress.setVisibility(View.GONE);
            tvPikup.setVisibility(View.VISIBLE);
            cardCity.setVisibility(View.GONE);
        }
        linearPay.setVisibility(View.VISIBLE);
    }

    public void setLiqPaqCheck(boolean liqPaqCheck) {
        if (!liqPaqCheck) {
            linearPayReciever.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style_check));
            linerLiqPay.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style));
        } else {
            linearPayReciever.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style));
            linerLiqPay.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style_check));
        }
        linearPib.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCheckout(List<OrderDesc> list) {

    }

    @Override
    public void refreshSumPrice(String price) {

    }

    @Override
    public void showOrderIsProcessed(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setDataSpinner(ArrayList<String> delivery, ArrayList<String> payment) {

    }


    @Override
    public void setSityes(String[] sityes) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void setAreas(AreasResponse areas) {

    }


    @Override
    public void setCities(List<String> cityList) {
        Log.d("Log.d", new Gson().toJson(cityList));

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>
                        (getContext(), android.R.layout.simple_spinner_dropdown_item, cityList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        autoCompleteCities.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void setWarehouses(List<String> warehouses) {
        Log.d("Log.d", new Gson().toJson(warehouses));
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>
                        (getContext(), android.R.layout.simple_spinner_dropdown_item, warehouses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        autoCompleteWarehouse.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg)
                .setPositiveButton("На головну", (dialog, id) -> startMainActivity(null));
        builder.create().show();
    }


    private void startMainActivity(String msg) {
        getActivity().finish();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }
}

