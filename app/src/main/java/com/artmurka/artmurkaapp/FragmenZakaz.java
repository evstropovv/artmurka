package com.artmurka.artmurkaapp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
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

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.OrderDesc;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.Areas.AreasResponse;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.Areas.Datum;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityResponse.Address;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityResponse.CityResponse;
import com.artmurka.artmurkaapp.Other.Spinner.SearchableSpinner;
import com.artmurka.artmurkaapp.Presenter.CheckoutPresenter;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.ICheckoutPresenter;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.ICheckoutFragment;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.widget.RxAutoCompleteTextView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class FragmenZakaz extends Fragment implements ICheckoutFragment {

    LinearLayout linearNovaPoshta, linerPikup, linearPayReciever, linerLiqPay;
    private TextView tvChoseAdress, tvPikup;
    private SearchableSpinner spinnerRegion;
    private AutoCompleteTextView spinnerCity;
    private boolean npCheck = false;
    private boolean liqPayCheck = false;
    private CardView cardRegion, cardCity, cardPostOffice;
    private ICheckoutPresenter checkoutPresenter;
    private Button btnPostCheckout;
    private EditText etPhone, etName, etLastName;
    private List<Datum> datumList;
    private List<String> cities;

    public FragmenZakaz() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_zakaz, container, false);
        // Inflate the layout for this fragment
        initUI(view);


        checkoutPresenter = new CheckoutPresenter(this);
        checkoutPresenter.getAreas();

        btnPostCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkoutPresenter.postCheckout(etPhone.getText().toString(),
                        etName.getText().toString() + " " + etLastName.getText().toString(),
                        "va.evstropov@gmail.com", (npCheck ? "2" : "1"), (liqPayCheck ? "2" : "1")
                );
            }
        });
        return view;
    }

    private void initUI(View view) {
        linearNovaPoshta = (LinearLayout) view.findViewById(R.id.linearNovaPoshta);
        linerPikup = (LinearLayout) view.findViewById(R.id.linerPikup);
        tvChoseAdress = (TextView) view.findViewById(R.id.tvChoseAdress);
        tvPikup = (TextView) view.findViewById(R.id.tvPikup);
        cardRegion = (CardView) view.findViewById(R.id.cardRegion);
        cardCity = (CardView) view.findViewById(R.id.cardCity);
        cardPostOffice = (CardView) view.findViewById(R.id.cardPostOffice);
        linearPayReciever = (LinearLayout) view.findViewById(R.id.linearPayReciever);
        linerLiqPay = (LinearLayout) view.findViewById(R.id.linerLiqPay);
        btnPostCheckout = (Button) view.findViewById(R.id.btnZakaz);
        etPhone = (EditText) view.findViewById(R.id.etPhone);
        etName = (EditText) view.findViewById(R.id.etName);
        etLastName = (EditText) view.findViewById(R.id.etLastName);
        spinnerRegion = (SearchableSpinner) view.findViewById(R.id.spinnerRegion);
        spinnerCity = (AutoCompleteTextView) view.findViewById(R.id.spinnerCity);
//        RxAutoCompleteTextView.threshold(spinnerCity).
        RxTextView.textChanges(spinnerCity).debounce(500, TimeUnit.MILLISECONDS).subscribe(text -> {
            //spinnerCity.setText(text);
            if (text.length() > 1) checkoutPresenter.getCities(text.toString());
        });

        //   spinnerCity.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
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
            }
        });
        linearPayReciever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liqPayCheck = false;
                setLiqPaqCheck(liqPayCheck);
            }
        });

    }

    void setCheckNP(boolean check) {
        if (!check) {
            linearNovaPoshta.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style_check));
            linerPikup.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style));
            tvChoseAdress.setVisibility(View.VISIBLE);
            tvPikup.setVisibility(View.GONE);
            cardRegion.setVisibility(View.VISIBLE);
            cardCity.setVisibility(View.VISIBLE);
            cardPostOffice.setVisibility(View.VISIBLE);

        } else {
            linearNovaPoshta.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style));
            linerPikup.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style_check));
            tvChoseAdress.setVisibility(View.GONE);
            tvPikup.setVisibility(View.VISIBLE);
            cardRegion.setVisibility(View.GONE);
            cardCity.setVisibility(View.GONE);
            cardPostOffice.setVisibility(View.GONE);
        }
    }

    public void setLiqPaqCheck(boolean liqPaqCheck) {
        if (!liqPaqCheck) {
            linearPayReciever.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style_check));
            linerLiqPay.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style));
        } else {
            linearPayReciever.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style));
            linerLiqPay.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style_check));
        }
    }

    @Override
    public void showCheckout(List<OrderDesc> list) {

    }

    @Override
    public void refreshSumPrice(String price) {

    }

    @Override
    public void showOrderIsProcessed(String msg) {

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

        datumList = areas.getData();
        ArrayAdapter<Datum> adapter =
                new ArrayAdapter<Datum>(getContext(), android.R.layout.simple_spinner_dropdown_item, datumList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerRegion.setAdapter(adapter);
        spinnerRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                //     checkoutPresenter.getCities(pos);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void setCities(CityResponse cityResponse) {
        spinnerCity.setVisibility(View.VISIBLE);
        cities = null;
        cities = new ArrayList<String>();
        List<Address> adressList = cityResponse.getData().get(0).getAddresses();
        Log.d("Log.d List<Address>", new Gson().toJson(adressList));
        if (adressList.size()>0){
            for (int i = 0; i < adressList.size()-1; i++) {
                cities.add(adressList.get(i).getSettlementTypeCode()+adressList.get(i).getMainDescription()+" ("+adressList.get(i).getArea()+")");
            }
        }

        Log.d("Log.d-", new Gson().toJson(cities));

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>
                        (getContext(), android.R.layout.simple_spinner_dropdown_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

