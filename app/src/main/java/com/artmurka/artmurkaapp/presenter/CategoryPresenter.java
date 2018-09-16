package com.artmurka.artmurkaapp.presenter;

import android.content.Context;
import android.util.Log;

import com.artmurka.artmurkaapp.data.model.modules.AllRequestOvservers;
import com.artmurka.artmurkaapp.data.model.interfacesmodel.IAllRequestObservers;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Example;
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Success;
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.ICategoryPresenter;
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ICategoryFragment;
import com.google.gson.Gson;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class CategoryPresenter implements ICategoryPresenter {

    private ICategoryFragment catFragment;
    private IAllRequestObservers model;
    private ArrayList<Success> successList;
    private Observable<Example> exampleObservable;


    public CategoryPresenter(ICategoryFragment fragment, Context context) {
        catFragment = fragment;

        model = new AllRequestOvservers();
    }

    @Override
    public void getCategoriesData(final boolean isUpdate) {
        if (successList == null) {
            resetData();
        } else {
            catFragment.showCategories(successList);
        }
    }

    private void resetData() {

        exampleObservable = model.getCategories();
        exampleObservable.subscribe(new Observer<Example>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Example value) {
                successList = value.getSuccess();
                if (successList.isEmpty()) catFragment.showError("Error, succes list is Empty");
                else catFragment.showCategories(successList);

                Log.d("Log.d", new Gson().toJson(value.getSuccess()));
            }

            @Override
            public void onError(Throwable e) {
                catFragment.showError("Щось пішло не так. Перезавантажити ?");

            }

            @Override
            public void onComplete() {
            }
        });
    }
}
