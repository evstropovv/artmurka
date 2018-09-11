package com.artmurka.artmurkaapp.views.fragments.interfaces;

import com.artmurka.artmurkaapp.model.pojo.itemlist.categories.Success;

import java.util.ArrayList;

public interface IFragmentCategoryChild {
    void showCategories(ArrayList<Success> categoriesList);
    void showError(String msg);
}
