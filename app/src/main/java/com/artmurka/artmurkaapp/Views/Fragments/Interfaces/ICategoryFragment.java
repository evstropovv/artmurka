package com.artmurka.artmurkaapp.Views.Fragments.Interfaces;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Categories.Success;

import java.util.ArrayList;


public interface ICategoryFragment {

    void showCategories(ArrayList<Success> categoriesList); //show categories

    void showError(String error); //show error, if error
}
