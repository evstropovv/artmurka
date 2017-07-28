package com.artmurka.artmurkaapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;

import ua.privatbank.paylibliqpay.ErrorCode;
import ua.privatbank.paylibliqpay.LiqPay;
import ua.privatbank.paylibliqpay.LiqPayCallBack;
import ua.privatbank.paylibliqpay.LiqPayUtil;


public class PayFragment extends Fragment {
    private String publicPayKey = BuildConfig.PUBLIC_PAY_KEY;
    private String privatePayKey = BuildConfig.PRIVATE_PAY_KEY;
    private TextView tvSumToPay;
    private EditText etCardNumber, etYear, etMonth, etCvv;
    private Button btnPay;

    public PayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay, container, false);
        tvSumToPay = (TextView) view.findViewById(R.id.tvSummToPay);
        etCardNumber = (EditText) view.findViewById(R.id.etCardNumber);
        etYear = (EditText) view.findViewById(R.id.etYear);
        etMonth = (EditText) view.findViewById(R.id.etMonth);
        etCvv = (EditText) view.findViewById(R.id.etCvv);
        btnPay = (Button) view.findViewById(R.id.btnPay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Thread t = new Thread(new Runnable() {
                    public void run() {
                        try {
                            HashMap<String, String> map = new HashMap<>();
                            map.put("version", "3"); //версия АПИ, последняя вроде бы как - 3
                            map.put("public_key", publicPayKey); //паблик ключ
                            map.put("action", "auth"); //тип оплаты
                            map.put("amount", "1"); //сумма платежа
                            map.put("currency", "UAH");  //валюта
                            map.put("description", "Тестовая оплата"); //Описание
                            map.put("order_id",  String.valueOf(Math.random()*999999));  //уникальный ИД покупки в магазине (с сайта)
                            map.put("language", "ru"); //язык
                            map.put("server_url", "https://artmurka.com");
                       //   map.put("card", etCardNumber.getText().toString());
                            map.put("card", "4119976003028165");
                                     //  map.put("card_exp_month", etMonth.getText().toString());
                            map.put("card_exp_month", "");
                         //   map.put("card_exp_year", etYear.getText().toString());
                            map.put("card_exp_year", "");
                            //  map.put("card_exp_cvv", etCvv.getText().toString());
                            map.put("card_exp_cvv", "");
                            LiqPay.api(getContext().getApplicationContext(), "auth", map, privatePayKey, new LiqPayCallBack() {
                                @Override
                                public void onResponseSuccess(String s) {
                                    JSONObject object = null;
                                    try {
                                        object = new JSONObject(s);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    if ("success".equals(object.optString("status"))) {
                                        // успех
                                        String cardToken = object.optString("card_token");
                                        Log.d("Log.d", "card_token " + cardToken);
                                    } else {
                                        Log.d("Log.d", "card pay Error ");
                                        Log.d("Log.d", object.toString());
                                    }
                                }

                                @Override
                                public void onResponceError(ErrorCode errorCode) {

                                }
                            });


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
            }
        });
        return view;
    }
}
