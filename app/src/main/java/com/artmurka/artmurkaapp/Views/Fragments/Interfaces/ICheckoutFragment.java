package com.artmurka.artmurkaapp.Views.Fragments.Interfaces;


import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.OrderDesc;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.Areas.AreasResponse;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.Areas.Datum;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityResponse.Address;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityResponse.CityResponse;

import java.util.ArrayList;
import java.util.List;

public interface ICheckoutFragment {
    void showCheckout(List<OrderDesc> list);
    void refreshSumPrice(String price);
    void showOrderIsProcessed(String msg);
    void setDataSpinner(ArrayList<String> delivery, ArrayList<String> payment);
    void setSityes(String[] sityes);
    void showMessage(String message);
    void setAreas(AreasResponse areas);
    void setCities(List<String> cities);
    void setWarehouses(List<String> warehouses);
}
