package com.artmurka.artmurkaapp.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artmurka.artmurkaapp.BuildConfig;
import com.artmurka.artmurkaapp.Modules.UcozApiModule;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Retrofit.ApiRetrofit;
import com.artmurka.artmurkaapp.Retrofit.Example;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CategoryFragment extends Fragment {


    public CategoryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loadInfo();
        return inflater.inflate(R.layout.fragment_category, container, false);

    }

    private void loadInfo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiRetrofit apiRetrofit = retrofit.create(ApiRetrofit.class);
        UcozApiModule ucoz = new UcozApiModule();

        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();
        mapForUcozModule.put("page", "categories");
        mapForUcozModule.put("method", "GET");
        mapForUcozModule.put("url", "uapi/shop/request");

        HashMap<String, String> confForRequest = ucoz.get(mapForUcozModule);

        apiRetrofit.getShopCategories(confForRequest.get("oauth_signature"),
                confForRequest.get("oauth_signature_method"),
                confForRequest.get("oauth_version"),
                confForRequest.get("consumer_key"),
                confForRequest.get("oauth_token"),
                confForRequest.get("oauth_nonce"),
                confForRequest.get("oauth_timestamp"),
                mapForUcozModule.get("page"))
                .enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                       // btn.setText(response.body().getSuccess().get(0).getCatDescr());

                    }
                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                    }
                });

    }

    @Override
    public void onStart() {
        super.onStart();



    }
}
