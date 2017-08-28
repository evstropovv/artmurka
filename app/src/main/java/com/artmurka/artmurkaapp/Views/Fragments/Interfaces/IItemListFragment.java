package com.artmurka.artmurkaapp.Views.Fragments.Interfaces;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.GoodsProperties;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Success;

import java.util.ArrayList;

public interface IItemListFragment {
    void showItemList(ArrayList<GoodsProperties> goodsProperties); //какой то эррейлист
    void showError(String error);
    void setTitle(String title);
}
