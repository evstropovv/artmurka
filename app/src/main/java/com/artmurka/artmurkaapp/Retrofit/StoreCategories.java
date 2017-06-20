package com.artmurka.artmurkaapp.Retrofit;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreCategories {

    @SerializedName("success")
    @Expose
    private List<OneCategory> categories = null;
    public List<OneCategory> getCategories() {
        return categories;
    }
}