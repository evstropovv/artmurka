package com.artmurka.artmurkaapp.android.views.fragments.interfaces;

import com.artmurka.artmurkaapp.model.pojo.itemlist.categories.Success;

import java.util.List;


public interface ICategoryFragment {

    void showCategories(List<Success> categoriesList); //show categories
    void setToolBarName(String name);
    void showError(String error); //show error, if error
}
