package com.artmurka.artmurkaapp.Views.Fragments.Interfaces;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Categories.Success;

import java.util.ArrayList;
import java.util.List;


public interface ICategoryFragment {

    void showCategories(List<Success> categoriesList); //show categories
    void setToolBarName(String name);
    void showError(String error); //show error, if error
}
