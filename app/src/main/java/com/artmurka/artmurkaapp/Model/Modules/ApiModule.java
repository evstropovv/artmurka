package com.artmurka.artmurkaapp.Model.Modules;

import com.artmurka.artmurkaapp.BuildConfig;
import com.artmurka.artmurkaapp.Model.Retrofit.ApiRetrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiModule {

    private static ApiRetrofit apiRetrofit;

    public static ApiRetrofit getClient(){
        if (apiRetrofit==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            apiRetrofit = retrofit.create(ApiRetrofit.class);
        }
        return apiRetrofit;
    }
}
