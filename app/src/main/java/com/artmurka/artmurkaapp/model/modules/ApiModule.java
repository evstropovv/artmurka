package com.artmurka.artmurkaapp.model.modules;

import com.artmurka.artmurkaapp.BuildConfig;
import com.artmurka.artmurkaapp.model.retrofit.ApiRetrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiModule {

    private static ApiRetrofit apiRetrofit;

    public static ApiRetrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();


        if (apiRetrofit==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BuildConfig.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            apiRetrofit = retrofit.create(ApiRetrofit.class);
        }
        return apiRetrofit;
    }
}
