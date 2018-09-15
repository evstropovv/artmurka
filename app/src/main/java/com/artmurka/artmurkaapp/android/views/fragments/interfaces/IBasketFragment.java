package com.artmurka.artmurkaapp.android.views.fragments.interfaces;

import com.artmurka.artmurkaapp.model.pojo.itemlist.itembasket.Item;

import java.util.List;


public interface IBasketFragment {
    void showError(String error);
    void showItemsInBasket(List<Item> items);
    void makeMessageInvisible(boolean b);
    void showPrice(String price);
}
