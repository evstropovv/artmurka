package com.artmurka.artmurkaapp.presenter;

import android.text.Html;
import android.util.Log;

import com.artmurka.artmurkaapp.model.interfacesmodel.IAboutGoods;
import com.artmurka.artmurkaapp.model.interfacesmodel.IBasket;
import com.artmurka.artmurkaapp.model.interfacesmodel.IWishList;
import com.artmurka.artmurkaapp.model.modules.AboutGoodsRequest;
import com.artmurka.artmurkaapp.model.modules.BasketRequest;
import com.artmurka.artmurkaapp.model.modules.WishListRequest;
import com.artmurka.artmurkaapp.model.pojo.itemlist.aboutgoods.AboutGood;
import com.artmurka.artmurkaapp.model.pojo.itemlist.aboutgoods.SizePhoto;
import com.artmurka.artmurkaapp.model.pojo.itemlist.aboutgoods.Success;
import com.artmurka.artmurkaapp.model.pojo.itemlist.good.Good;
import com.artmurka.artmurkaapp.model.pojo.itemlist.itemlist.GoodsProperties;
import com.artmurka.artmurkaapp.model.pojo.itemlist.itembasket.BasketItems;
import com.artmurka.artmurkaapp.model.pojo.itemlist.itemlist.SuccessExample;
import com.artmurka.artmurkaapp.model.pojo.itemlist.wishList.GoodsListDescription;
import com.artmurka.artmurkaapp.model.pojo.itemlist.wishList.WishList;
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IAboutGoodsPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.views.fragments.interfaces.IFragmentAboutGoods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutGoodsPresenter implements IAboutGoodsPresenter {

    private IFragmentAboutGoods fragment;
    private String goodsId;

    public AboutGoodsPresenter(IFragmentAboutGoods fr) {
        this.fragment = fr;
    }

    @Override
    public void getDataAboutGoods(String id) {
        this.goodsId = id;
        IAboutGoods model = new AboutGoodsRequest();
        Call<AboutGood> observable = model.getDataAboutGood(id);
        observable.enqueue(new Callback<AboutGood>() {
            @Override
            public void onResponse(Call<AboutGood> call, Response<AboutGood> response) {
                Success aboutGood = response.body().getSuccess();
                fragment.setName(aboutGood.getEntryTitle());
                fragment.setDescription(Html.fromHtml(aboutGood.getEntryDescription()).toString());
                fragment.setPrice(aboutGood.getEntryPrice().getPriceRaw() + " грн.");
                HashMap<String, SizePhoto> map = aboutGood.getEntryPhoto().getOthersPhoto();
                map.put(aboutGood.getEntryPhoto().getNumPhotos()+"", new SizePhoto(aboutGood.getEntryPhoto().getDefPhoto().getPhoto()));
                fragment.setPhoto(getImageList(map));
                fragment.getDataForRecyclerView(response.body().getSuccess().getEntryCat().getUrl());
                fragment.setWishButton(aboutGood.getEntryIsInWishlist() == 1 ? true : false);
                fragment.setBasketButton(aboutGood.getEntryIsInBasket() > 0 ? true : false);
                Log.d("Log.d", "getdata - success");
            }

            @Override
            public void onFailure(Call<AboutGood> call, Throwable t) {
                try2(id);
                Log.d("Log.d", "getdata - fail");
                Log.d("Log.d", t.getMessage());
            }
        });
    }

    private void try2(String id){
        IAboutGoods model = new AboutGoodsRequest();
        Call<Good> observable = model.getDataGood(id);
        observable.enqueue(new Callback<Good>() {
            @Override
            public void onResponse(Call<Good> call, Response<Good> response) {

                com.artmurka.artmurkaapp.model.pojo.itemlist.good.Success aboutGood = response.body().getSuccess();
                fragment.setName(aboutGood.getEntryTitle());
                fragment.setDescription(Html.fromHtml(aboutGood.getEntryDescription()).toString());
                fragment.setPrice(aboutGood.getEntryPrice().getPriceRaw() + " грн.");
                HashMap<String, SizePhoto> map = new HashMap<String, SizePhoto>();
                map.put(aboutGood.getEntryPhoto().getNumPhotos()+"", new SizePhoto(aboutGood.getEntryPhoto().getDefPhoto().getPhoto()));
                fragment.setPhoto(getImageList(map));
                fragment.getDataForRecyclerView(response.body().getSuccess().getEntryCat().getUrl());
                fragment.setWishButton(aboutGood.getEntryIsInWishlist() == 1 ? true : false);
                fragment.setBasketButton(aboutGood.getEntryIsInBasket() > 0 ? true : false);
                Log.d("Log.d", "getdata2 - success");
            }

            @Override
            public void onFailure(Call<Good> call, Throwable t) {
                Log.d("Log.d", "getdata2 - fail");
                Log.d("Log.d", t.getMessage());
            }
        });
    }

    @Override
    public void getCategoryData(String category) {
        IAboutGoods model = new AboutGoodsRequest();
        Observable<SuccessExample> observable = model.getItemList(category, "1");
        observable.subscribe(new Observer<SuccessExample>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(SuccessExample value) {
                fragment.setDataForRecyclerView(getGoodsList(value.getSuccess().getGoodsList()));
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
    public void btnClicked(int buttonId) {
        switch (buttonId) {
            case R.id.ivWish:
                //добавить в список желаний
                IWishList iWishList = new WishListRequest();
                Call<WishList> obs = iWishList.toWishList(goodsId);
                obs.enqueue(new Callback<WishList>() {
                    @Override
                    public void onResponse(Call<WishList> call, Response<WishList> response) {
                        try {
                            for (Map.Entry<String, GoodsListDescription> map : response.body().getSuccess().getGoodsList().entrySet()) {
                                if (map.getValue().getEntryId().equals(goodsId)) {
                                    fragment.setWishButton(true);
                                    break;
                                } else {
                                    fragment.setWishButton(false);
                                }
                            }
                        } catch (NullPointerException e) {
                            fragment.setWishButton(false);
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<WishList> call, Throwable t) {
                    }
                });

                break;
            case R.id.ivBasket:
                //кнопка добавления в корзину
                IBasket basket = new BasketRequest();
                Observable<BasketItems> observable = basket.toBasket(goodsId);
                observable.subscribe(new Observer<BasketItems>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(BasketItems value) {
                        fragment.setBasketButton(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {}
                });
                break;
            default:
                break;
        }
    }

    private ArrayList<String> getImageList(HashMap<String, SizePhoto> map) {

        ArrayList<String> photosUrl = new ArrayList<>();
        for (String key : map.keySet()) {
            photosUrl.add(map.get(key).getPhoto());
        }
        return photosUrl;
    }


    private ArrayList<GoodsProperties> getGoodsList(TreeMap<String, GoodsProperties> map) {
        ArrayList<GoodsProperties> goodsProperties = new ArrayList<>();
        for (String key : map.keySet()) {
            goodsProperties.add(map.get(key));
        }
        return goodsProperties;
    }
}
