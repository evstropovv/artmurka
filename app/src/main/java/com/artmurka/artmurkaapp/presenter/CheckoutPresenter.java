package com.artmurka.artmurkaapp.presenter;

import android.util.Log;

import com.artmurka.artmurkaapp.BuildConfig;
import com.artmurka.artmurkaapp.data.model.interfacesmodel.ICheckoutRequest;
import com.artmurka.artmurkaapp.data.model.modules.ApiModuleNovaPoshta;
import com.artmurka.artmurkaapp.data.model.modules.CheckoutRequest;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.DeliveryDescription;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.OrderDesc;

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.PaymentDescription;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkoutresponse.CheckoutResponse;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.Areas.Datum;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.CityRequest.City;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.CityRequest.MethodProperties;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.CityResponse.Address;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.WarehousesRequest.WarehouseRequest;
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.ICheckoutPresenter;
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ICheckoutFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
    private List<Address> cities;
    private List<com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.WarehousesResponse.Datum> warehouseList;
    private Boolean isViewDetached = false;

    List<Disposable> disposables = new ArrayList<>();


    private List<Datum> datumList;

    public CheckoutPresenter(ICheckoutFragment fragment) {
        this.fragment = fragment;
        request = new CheckoutRequest();
    }

    @Override
    public void getData() {
        Disposable disposable = request.getCheckoutData().map(checkoutAllGoods -> checkoutAllGoods.getSuccess()).subscribe(success -> {
            Log.d("Log.d", "ResponsCheck");
            fragment.showCheckout(getList(success.getOrderContent().getOrderGoods()));
            fragment.refreshSumPrice(success.getOrderData().getOrderAmount().getAmountRaw().toString());

            //отображаем значения для выбора оплаты и выбора доставки
            if (deliveryList == null) {
                deliveryList = new ArrayList<>();
                paymentList = new ArrayList<>();

                deliveryMap = success.getDeliveryList();
                payMap = success.getPaymentList();

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
        }, throwable -> {
        });
        disposables.add(disposable);
    }


    @Override
    public void postCheckout(String telephone, String message, String email, String pay, String delivery) {
        Log.d("Log.d", "postCheckout");


        Call<CheckoutResponse> call = request.postCheckout(telephone, message, email, pay, delivery);
        call.enqueue(new Callback<CheckoutResponse>() {
            @Override
            public void onResponse(Call<CheckoutResponse> call, Response<CheckoutResponse> response) {
                if (response.code() == 200) {
                    if (response.body().getError() != null) {
                        fragment.showOrderIsProcessed(response.body().getError().getMsg());
                    }

                    if (response.body().getSuccess() != null) {
                        fragment.showDialog(response.body().getSuccess().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<CheckoutResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void cityChanged(String msg) {
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
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void selectCity(Integer cityPosition) {
        String cityRef = cities.get(cityPosition).getDeliveryCity();
        Log.d("Log.d", "cityRef" + cityRef);

        WarehouseRequest warehouseRequest = new WarehouseRequest();
        warehouseRequest.setApiKey(BuildConfig.NP_API_KEY);
        warehouseRequest.setCalledMethod("getWarehouses"); //getSettlements
        warehouseRequest.setModelName("AddressGeneral");
        warehouseRequest.setMethodProperties(
                new com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.WarehousesRequest.MethodProperties(cityRef));
        Disposable disposable = ApiModuleNovaPoshta.getClient().getWarehouses(warehouseRequest)
                .map(warehouseResponse -> warehouseResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    this.warehouseList = data;
                    List<String> warehouses = new ArrayList<>();
                    for (int i = 0; i < data.size(); i++) {
                        warehouses.add(data.get(i).getNumber() + " - " + data.get(i).getDescription());

                    }
                    Log.d("Log.d", "setWarehouses " + new Gson().toJson(warehouses));
                    fragment.setWarehouses(warehouses);

                }, error -> {
                });
        disposables.add(disposable);
    }

    @Override
    public void getAreas() {
    }

    @Override
    public void getCities(String cityName) {
        City city = new City();
        city.setApiKey(BuildConfig.NP_API_KEY);
        city.setCalledMethod("searchSettlements");
        // Log.d("Log.d", "select position "+datumList.get(pos).getAreasCenter());
        city.setMethodProperties(new MethodProperties(cityName));
        city.setModelName("Address");

        Disposable disposable = ApiModuleNovaPoshta.getClient().searhCity(city)
                .map(datumList -> datumList.getData().get(0).getAddresses())
                .doOnNext(addresses -> Log.d("Log.d", "list address size " + addresses.size() + ""))
//                .flatMap(addresses -> Flowable.fromIterable(addresses))
//                .filter(addres -> {
//                    return addres.getWarehouses() > 0;
//                })
//                .toSortedList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(addresses -> {
                            cities = addresses;
                            Log.d("Log.d", "list address size subscribe**" + addresses.size() + "");

                            if (cities.size() > 0) {
                                List<String> cityStringList = new ArrayList<>();
                                for (int i = 0; i < addresses.size(); i++) {
                                    //отображаем города у которых 1+ склад новой почты
                                    cityStringList.add(cities.get(i).getMainDescription());
                                }
                                fragment.setCities(cityStringList);
                            }
                        }
                        , throwable -> {
                        });
        disposables.add(disposable);
    }

    @Override
    public void selectWarehouse(Integer warehousePosition) {

    }

    @Override
    public void detachView() {
        isViewDetached = true;
        for (int i = 0; i <disposables.size() ; i++) {
            if ((disposables.get(i)!=null) && (!disposables.get(i).isDisposed())){
                disposables.get(i).dispose();
            }
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
