package com.artmurka.artmurkaapp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.artmurka.artmurkaapp.Model.Modules.ApiModuleNovaPoshta;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Areas.Areas;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.OrderDesc;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.Areas.AreasResponse;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.Areas.Datum;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityRequest.City;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityRequest.MethodProperties;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityResponse.CityResponse;
import com.artmurka.artmurkaapp.Other.Spinner.SearchableSpinner;
import com.artmurka.artmurkaapp.Presenter.CheckoutPresenter;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.ICheckoutPresenter;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.ICheckoutFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmenZakaz extends Fragment implements ICheckoutFragment {

    LinearLayout linearNovaPoshta, linerPikup, linearPayReciever, linerLiqPay;
    private TextView tvChoseAdress, tvPikup;
    private SearchableSpinner spinnerRegion;
    private boolean npCheck = false;
    private boolean liqPayCheck = false;
    private CardView cardRegion, cardCity, cardPostOffice;
    private ICheckoutPresenter checkoutPresenter;
    private Button btnPostCheckout;
    private EditText etPhone, etName, etLastName;
    private List<Datum> datumList;
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
                        etName.getText().toString() +" "+ etLastName.getText().toString(),
                        "va.evstropov@gmail.com",  (npCheck?"2":"1"),(liqPayCheck?"2":"1")
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
        btnPostCheckout = (Button)view.findViewById(R.id.btnZakaz);
        etPhone = (EditText)view.findViewById(R.id.etPhone);
        etName = (EditText)view.findViewById(R.id.etName);
        etLastName = (EditText)view.findViewById(R.id.etLastName);
        spinnerRegion = (SearchableSpinner) view.findViewById(R.id.spinnerRegion);
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
                City city = new City();
                city.setApiKey(BuildConfig.NP_API_KEY);
                city.setCalledMethod("getSettlements");
                Toast.makeText(getContext(),datumList.get(pos).getDescription(), Toast.LENGTH_LONG).show();
                Log.d("Log.d", "select position "+datumList.get(pos).getAreasCenter());
                city.setMethodProperties(new MethodProperties(datumList.get(pos).getRef()+ "")); //TODO
                city.setModelName("AddressGeneral");
                Call<Areas> cityResponse = ApiModuleNovaPoshta.getClient().searhCity(city);
                cityResponse.enqueue(new Callback<Areas>() {
                    @Override
                    public void onResponse(Call<Areas> call, Response<Areas> response) {

                        Log.d("Log.d", new Gson().toJson(response.body()));
                    }

                    @Override
                    public void onFailure(Call<Areas> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
