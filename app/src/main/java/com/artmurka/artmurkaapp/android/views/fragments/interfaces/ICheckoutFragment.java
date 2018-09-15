package com.artmurka.artmurkaapp.android.views.fragments.interfaces;


import com.artmurka.artmurkaapp.model.pojo.itemlist.checkout.OrderDesc;
import com.artmurka.artmurkaapp.model.pojo.itemlist.novaposhta.Areas.AreasResponse;

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
    void showDialog(String msg);
}
