package com.artmurka.artmurkaapp.presenter;


import com.artmurka.artmurkaapp.model.interfacesmodel.IBasket;
import com.artmurka.artmurkaapp.model.modules.BasketRequest;
import com.artmurka.artmurkaapp.model.pojo.itemlist.itembasket.BasketItems;
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IBasketPresenter;
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IBasketFragment;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class BasketPresenter implements IBasketPresenter {

    IBasketFragment fragment;

    public BasketPresenter(IBasketFragment fragment){
        this.fragment = fragment;
    }


    @Override
    public void getDataForbasket() {

        final IBasket basket = new BasketRequest();
        Observable<BasketItems> observable = basket.getItemInBasket();
        observable.subscribe(new Observer<BasketItems>() {
            @Override
            public void onSubscribe(Disposable d) {}

            @Override
            public void onNext(BasketItems value) {
                if (fragment!=null){
                    fragment.showItemsInBasket(value.getSuccess().getBasket().getItems());
                    fragment.makeMessageInvisible(true);
                    fragment.showPrice(value.getSuccess().getBasket().getTotal());
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

    @Override
    public void onDetach() {
        fragment = null;
    }
}
