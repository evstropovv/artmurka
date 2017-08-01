package com.artmurka.artmurkaapp;


import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
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
            public void onClick(final View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("version", "3");
                        map.put("public_key", publicPayKey);
                        map.put("action", "pay");
                        map.put("amount", "1");
                        map.put("currency", "UAH");
                        map.put("description", "account top-up");
                        map.put("order_id", "sdfsdf"+Math.random());
                        map.put("language", "uk");

                        map.put("server_url", "https://www.liqpay.ua/ru/checkout/card/i32727180241");
                        LiqPay.checkout(v.getContext().getApplicationContext(), map, privatePayKey, new LiqPayCallBack() {
                            @Override
                            public void onResponseSuccess(String s) {
                                Log.v("tag_order", s);
                            }

                            @Override
                            public void onResponceError(ErrorCode errorCode) {
                                Log.v("tag_liqpay", errorCode.toString());
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                    }
                                }).start();

                            }
                        });

                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
            }
        });
        return view;
    }
}
