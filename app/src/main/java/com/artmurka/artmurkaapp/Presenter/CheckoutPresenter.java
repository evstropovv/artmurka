package com.artmurka.artmurkaapp.Presenter;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.artmurka.artmurkaapp.BuildConfig;
import com.artmurka.artmurkaapp.Model.Databases.Preferences;
import com.artmurka.artmurkaapp.Model.InterfacesModel.ICheckoutRequest;
import com.artmurka.artmurkaapp.Model.Modules.ApiModuleNovaPoshta;
import com.artmurka.artmurkaapp.Model.Modules.CheckoutRequest;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.CheckoutAllGoods;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.DeliveryDescription;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.OrderDesc;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.PaymentDescription;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.Areas.AreasRequest;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.Areas.AreasResponse;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.Areas.Datum;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityRequest.City;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityRequest.MethodProperties;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityResponse.Address;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityResponse.CityResponse;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.WarehousesRequest.WarehouseRequest;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.WarehousesResponse.WarehouseResponse;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.ICheckoutPresenter;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.ICheckoutFragment;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Categories.Success;
import com.google.gson.Gson;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
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
    private Boolean isViewDetached = false;


    private List<Datum> datumList;

    public CheckoutPresenter(ICheckoutFragment fragment) {
        this.fragment = fragment;
        request = new CheckoutRequest();
    }

    @Override
    public void getData() {
        Observable<CheckoutAllGoods> checkoutObservable = request.getCheckoutData();
        checkoutObservable.map(checkoutAllGoods -> checkoutAllGoods.getSuccess()).subscribe(success -> {
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
//        City city = new City();
//        city.setApiKey(BuildConfig.NP_API_KEY);
//        city.setCalledMethod("searchSettlements");
//       // city.setMethodProperties(new MethodProperties(msg + "", 4));
//        city.setModelName("Info");
//        Call<CityResponse> cityResponse =  ApiModuleNovaPoshta.getClient().searhCity(city);
//        cityResponse.enqueue(new Callback<CityResponse>() {
//            @Override
//            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
//                try {
//                    for (int i = 0; i < response.body().getData().get(0).getAddresses().size(); i++) {
//                        cities[i] = response.body().getData().get(0).getAddresses().get(i).getSettlementTypeCode()
//                                + response.body().getData().get(0).getAddresses().get(i).getMainDescription()
//                                + ", " + response.body().getData().get(0).getAddresses().get(i).getArea();
//                    }
//                    fragment.setSityes(cities);
//                } catch (IndexOutOfBoundsException e) {
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CityResponse> call, Throwable t) {
//            }
//        });
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
        String cityRef = cities.get(cityPosition).getRef();
        WarehouseRequest warehouseRequest = new WarehouseRequest();
        warehouseRequest.setApiKey(BuildConfig.NP_API_KEY);
        warehouseRequest.setCalledMethod("getSettlements");
        warehouseRequest.setModelName("AddressGeneral");
        warehouseRequest.setMethodProperties(
                new com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.WarehousesRequest.MethodProperties(cityRef));

        //
        ApiModuleNovaPoshta.getClient().getWarehouses(warehouseRequest)
                .map(warehouseResponse -> warehouseResponse.getData())
                .flatMap(data -> Flowable.fromIterable(data))
                .toSortedList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    List<String> warehouses = new ArrayList<>();
                    for (int i = 0; i < data.size() ; i++) {
                        warehouses.add(data.get(i).getRegionsDescription());
                    }
                    fragment.setWarehouses(warehouses);

                }, error -> {});
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
        ApiModuleNovaPoshta.getClient().searhCity(city)
                .map(datumList -> datumList.getData().get(0).getAddresses())
                .flatMap(addresses -> Flowable.fromIterable(addresses))
                .filter(addres -> {
                    return addres.getWarehouses() > 0;
                })
                .toSortedList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(addresses -> {
                    cities = addresses;
                    if (cities.size() > 0) {
                        List<String> cityStringList = new ArrayList<>();
                        for (int i = 0; i < cities.size(); i++) {
                            //отображаем города у которых 1+ склад новой почты
                            cityStringList.add(cities.get(i).getMainDescription() + " " + cities.get(i).getWarehouses());
                        }
                        fragment.setCities(cityStringList);
                    }
                }
                , throwable -> {
                });
    }

    @Override
    public void detachView() {
        isViewDetached = true;
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
