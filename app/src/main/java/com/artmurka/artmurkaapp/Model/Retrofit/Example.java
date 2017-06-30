package com.artmurka.artmurkaapp.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Example {

    @SerializedName("success")
    @Expose
    private ArrayList<Success> success = null;
    public ArrayList<Success> getSuccess() {
        return success;
    }
}
