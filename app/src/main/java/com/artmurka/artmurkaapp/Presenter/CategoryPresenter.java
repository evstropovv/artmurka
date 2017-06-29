package com.artmurka.artmurkaapp.Presenter;

import com.artmurka.artmurkaapp.Model.Modules.AllRequestOvservers;
import com.artmurka.artmurkaapp.Model.Modules.IAllRequestObservers;
import com.artmurka.artmurkaapp.Model.Retrofit.Example;
import com.artmurka.artmurkaapp.Model.Retrofit.Success;
import com.artmurka.artmurkaapp.Views.Fragments.ICategoryFragment;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class CategoryPresenter implements ICategoryPresenter {

    private ICategoryFragment _catFragment;
    private IAllRequestObservers _model;

    public CategoryPresenter(ICategoryFragment fragment){
        _catFragment = fragment;
        _model = new AllRequestOvservers();
    }


    @Override
    public void getCategoriesData(boolean isUpdate) {

        Observable<Example> exampleObservable = _model.getCategories();
        exampleObservable.subscribe(new Observer<Example>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Example value) {
                List<Success> successes = value.getSuccess();

                if (successes.isEmpty()){
                    _catFragment.showError("Error, succes list is Empty");
                }
                else{
                    _catFragment.showCategories(successes);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
