package com.artmurka.artmurkaapp.Views.Fragments;

import com.artmurka.artmurkaapp.Model.Retrofit.Example;
import com.artmurka.artmurkaapp.Model.Retrofit.Success;

import java.util.ArrayList;


public interface ICategoryFragment {

    void showCategories(ArrayList<Success> categoriesList); //show categories
    void onGetCategoryClick(); // when click on Category
    void showError(String error); //show error, if error
}
