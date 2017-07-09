package com.artmurka.artmurkaapp.Views.Fragments.Interfaces;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.GoodsProperties;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Success;

import java.util.ArrayList;

/**
 * Created by Вася on 05.07.2017.
 */

public interface IItemListFragment {
    void showItemList(ArrayList<GoodsProperties> goodsProperties); //какой то эррейлист
    void showError(String error);
}
