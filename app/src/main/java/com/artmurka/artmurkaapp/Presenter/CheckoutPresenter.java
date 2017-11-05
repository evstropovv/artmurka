package com.artmurka.artmurkaapp.Presenter;

import android.util.Log;

import com.artmurka.artmurkaapp.BuildConfig;
import com.artmurka.artmurkaapp.Model.InterfacesModel.ICheckoutRequest;
import com.artmurka.artmurkaapp.Model.Modules.ApiModuleNovaPoshta;
import com.artmurka.artmurkaapp.Model.Modules.CheckoutRequest;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.CheckoutAllGoods;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.DeliveryDescription;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.OrderDesc;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.PaymentDescription;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityRequest.City;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityRequest.MethodProperties;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityResponse.CityResponse;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.ICheckoutPresenter;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.ICheckoutFragment;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Categories.Success;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutPresenter implements ICheckoutPresenter {
    private ICheckoutFragment fragment;
    private boolean isTextChanged = false;
    private ICheckoutRequest request;
    private ArrayList<String> deliveryList;
    private ArrayList<String> paymentList;
    private HashMap<String, DeliveryDescription> deliveryMap;
    private HashMap<String, PaymentDescription> payMap;
    private String cities[] = new String[4];

    public CheckoutPresenter(ICheckoutFragment fragment) {
        this.fragment = fragment;
        request = new CheckoutRequest();
    }

    @Override
    public void getData() {

        Call<CheckoutAllGoods> call = request.getCheckoutData();
        call.enqueue(new Callback<CheckoutAllGoods>() {
            @Override
            public void onResponse(Call<CheckoutAllGoods> call, Response<CheckoutAllGoods> response) {
                //отображаем все заказы
                fragment.showCheckout(getList(response.body().getSuccess().getOrderContent().getOrderGoods()));
                //обновляем сумму заказа
                fragment.refreshSumPrice(response.body().getSuccess().getOrderData().getOrderAmount().getAmountRaw().toString() + " грн");

                //отображаем значения для выбора оплаты и выбора доставки
                if (deliveryList == null) {
                    deliveryList = new ArrayList<>();
                    paymentList = new ArrayList<>();

                    deliveryMap = response.body().getSuccess().getDeliveryList();
                    payMap = response.body().getSuccess().getPaymentList();

                    for (int i = 0; i < deliveryMap.size() + 1; i++) {
                        if (deliveryMap.get(String.valueOf(i)) != null) {
                            deliveryList.add(deliveryMap.get(String.valueOf(i)).getName());
                        }
                    }

                    for (int i = 0; i < payMap.size() + 1; i++) {
                        if (payMap.get(String.valueOf(i)) != null) {
                            paymentList.add(payMap.get(String.valueOf(i)).getName());
                        }
                    }
                    fragment.setDataSpinner(deliveryList, paymentList);
                }
            }

            @Override
            public void onFailure(Call<CheckoutAllGoods> call, Throwable t) {
            }
        });
    }

    @Override
    public void postCheckout(String telephone, String message, String email, String pay, String delivery) {
        Log.d("Log.d", "postCheckout");
        Call<Success> call = request.postCheckout(telephone, message, email, pay, delivery);
        call.enqueue(new Callback<Success>() {
            @Override
            public void onResponse(Call<Success> call, Response<Success> response) {
                if (response.code() == 200) {
                    fragment.showOrderIsProcessed(response.message());
                }
            }
            @Override
            public void onFailure(Call<Success> call, Throwable t) {
            }
        });
    }

    @Override
    public void cityChanged(String msg) {
        City city = new City();
        city.setApiKey(BuildConfig.NP_API_KEY);
        city.setCalledMethod("searchSettlements");
        city.setMethodProperties(new MethodProperties(msg + "", 4));
        city.setModelName("Address");
        Call<CityResponse> cityResponse = ApiModuleNovaPoshta.getClient().searhCity(city);
        cityResponse.enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                for (int i = 0; i < response.body().getData().get(0).getAddresses().size(); i++) {
                    cities[i] = response.body().getData().get(0).getAddresses().get(i).getSettlementTypeCode()
                            + response.body().getData().get(0).getAddresses().get(i).getMainDescription()
                            + ", " + response.body().getData().get(0).getAddresses().get(i).getArea();
                }
                fragment.setSityes(cities);
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public Boolean isEmailValid(String email) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
    }

    @Override
    public boolean isValidPhone(String phone) {
        String expression = "^([0-9\\+]|\\(\\d{1,3}\\))[0-9\\-\\. ]{8,15}$";
        CharSequence inputString = phone;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }
    }


    private List<OrderDesc> getList(HashMap<String, OrderDesc> map) {
        List<OrderDesc> answerList = new ArrayList<>();
        for (String key : map.keySet()) {
            OrderDesc desc = map.get(key);
            desc.setOrderPosition(key);
            answerList.add(desc);
        }
        return answerList;
    }


}
