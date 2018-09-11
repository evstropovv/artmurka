package com.artmurka.artmurkaapp.other;

import android.content.Context;
import android.util.Log;

import com.artmurka.artmurkaapp.BuildConfig;

import java.util.HashMap;

import ua.privatbank.paylibliqpay.ErrorCode;
import ua.privatbank.paylibliqpay.LiqPay;
import ua.privatbank.paylibliqpay.LiqPayCallBack;

public class PayLiq extends Thread {
    private Context context;
    private final String publicPayKey = BuildConfig.PUBLIC_PAY_KEY;
    private final String privatePayKey = BuildConfig.PRIVATE_PAY_KEY;
    private HashMap<String, String> parametrs;
    //new PayLiq(v.getContext()).start();

    public PayLiq(Context context, HashMap<String, String> parametrs) {
        this.context = context;
        this.parametrs = parametrs;
    }

    @Override
    public void run() {
        super.run();
        HashMap<String, String> map = new HashMap<>();
        map.put("version", "3"); //версия АПИ, последняя вроде бы как - 3
        map.put("public_key", publicPayKey); //паблик ключ
        map.put("action", "auth"); //тип оплаты
        map.put("amount", parametrs.get("amount")); //сумма платежа
        map.put("currency", "UAH");  //валюта
        map.put("description", "Оплата товару. Номер заказу " + parametrs.get("order_id")); //Описание
        map.put("order_id",  parametrs.get("order_id"));  //уникальный ИД покупки в магазине (с сайта)
        map.put("language", "ru"); //язык
        //   map.put("card", etCardNumber.getText().toString());
        map.put("server_url", "https://www.liqpay.ua/ru/checkout/card/i32727180241");
        LiqPay.checkout(context.getApplicationContext(), map, privatePayKey, new LiqPayCallBack() {
            @Override
            public void onResponseSuccess(String s) {
                Log.v("Log.d", s);
            }
            @Override
            public void onResponceError(ErrorCode errorCode) {
                Log.v("Log.d","error liqpay: "+ errorCode.toString());}
        });
    }
}
