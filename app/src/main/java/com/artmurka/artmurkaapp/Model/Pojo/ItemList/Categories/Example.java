package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class Example {

    @SerializedName("success")
    @Expose
    private ArrayList<Success> success = null;
    public ArrayList<Success> getSuccess() {
        return success;
    }
}
