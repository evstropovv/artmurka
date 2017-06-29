package com.artmurka.artmurkaapp.Views.Fragments;

import com.artmurka.artmurkaapp.Model.Retrofit.Example;
import com.artmurka.artmurkaapp.Model.Retrofit.Success;

import java.util.List;

/**
 * Created by Вася on 29.06.2017.
 */

public interface ICategoryFragment {

    void showCategories(List<Success> categoriesList); //show categories
    void onGetCategoryClick(); // when click on Category
    void showError(String error); //show error, if error
}
