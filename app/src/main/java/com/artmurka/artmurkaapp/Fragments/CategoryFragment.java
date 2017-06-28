package com.artmurka.artmurkaapp.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artmurka.artmurkaapp.Adapters.RVcategoryAdapter;
import com.artmurka.artmurkaapp.BuildConfig;
import com.artmurka.artmurkaapp.Modules.UcozApiModule;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Retrofit.ApiRetrofit;
import com.artmurka.artmurkaapp.Retrofit.Example;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class CategoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager recyclerLayoutManager;

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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiRetrofit apiRetrofit = retrofit.create(ApiRetrofit.class);

        UcozApiModule ucoz = new UcozApiModule();

        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();
        mapForUcozModule.put("page", "categories");
        mapForUcozModule.put("method", "GET");
        mapForUcozModule.put("url", "uapi/shop/request");

        //Getting all token for autorization.
        HashMap<String, String> confForRequest = ucoz.get(mapForUcozModule);

        Observable<Example> exampleObservable = apiRetrofit.getShopCategories(confForRequest.get("oauth_signature"),
                confForRequest.get("oauth_signature_method"),
                confForRequest.get("oauth_version"),
                confForRequest.get("consumer_key"),
                confForRequest.get("oauth_token"),
                confForRequest.get("oauth_nonce"),
                confForRequest.get("oauth_timestamp"),
                mapForUcozModule.get("page"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        exampleObservable.subscribe(new Observer<Example>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Example value) {
                if (getResources().getConfiguration().orientation == 1) {
                    recyclerLayoutManager = new GridLayoutManager(getActivity(), 2);
                } else {
                    recyclerLayoutManager = new GridLayoutManager(getActivity(), 3);
                }
                recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(recyclerLayoutManager);
                recyclerAdapter = new RVcategoryAdapter(getActivity(), value.getSuccess());
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
