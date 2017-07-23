package com.artmurka.artmurkaapp.Views.Fragments.Interfaces;


import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Checkout.OrderDesc;

import java.util.ArrayList;
import java.util.List;

public interface ICheckoutFragment {
    void showCheckout(List<OrderDesc> list);
    void refreshSumPrice(String price);
    void showOrderIsProcessed(String msg);
    void setDataSpinner(ArrayList<String> delivery, ArrayList<String> payment);
}
