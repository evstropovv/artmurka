package com.artmurka.artmurkaapp.Views.Fragments.Interfaces;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemBasket.Item;

import java.util.ArrayList;
import java.util.List;


public interface IBasketFragment {
    void showError(String error);
    void showItemsInBasket(List<Item> items);
    void makeMessageInvisible(boolean b);
    void showPrice(String price);
}
