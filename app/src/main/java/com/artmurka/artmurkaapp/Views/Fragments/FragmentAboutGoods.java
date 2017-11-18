package com.artmurka.artmurkaapp.Views.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemList.GoodsProperties;
import com.artmurka.artmurkaapp.Presenter.AboutGoodsPresenter;
import com.artmurka.artmurkaapp.Presenter.Adapters.RVitemListAdapterAboutGoods;
import com.artmurka.artmurkaapp.Presenter.Adapters.ViewPagerAdapter;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.IAboutGoodsPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.IFragmentAboutGoods;

import java.util.ArrayList;


public class FragmentAboutGoods extends Fragment implements IFragmentAboutGoods {

    IAboutGoodsPresenter presenter;
    private final String ID = "id";


    private TextView tvName, tvPrice, tvDescription;
    private ImageView ivPhoto, ivWish, ivBasket;
    private ViewPager viewPager;
    private View view;
    private RecyclerView recyclerView;
    private RVitemListAdapterAboutGoods recyclerAdapter;

    public FragmentAboutGoods() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_about_goods, container, false);
        if (presenter == null) {
            presenter = new AboutGoodsPresenter(this);
        }
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        ivPhoto = (ImageView) view.findViewById(R.id.ivItemPhoto);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        ivWish = (ImageView) view.findViewById(R.id.ivWish);
        ivWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RotateAnimation anim = new RotateAnimation(-10, 10, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setInterpolator(new LinearInterpolator());
                anim.setRepeatCount(Animation.RELATIVE_TO_PARENT);
                anim.setDuration(100);
                v.startAnimation(anim);

                presenter.btnClicked(v.getId());
            }
        });
        ivBasket = (ImageView) view.findViewById(R.id.ivBasket);
        ivBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RotateAnimation anim = new RotateAnimation(-10, 10, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setInterpolator(new LinearInterpolator());
                anim.setRepeatCount(Animation.RELATIVE_TO_PARENT);
                anim.setDuration(100);
                v.startAnimation(anim);
                presenter.btnClicked(v.getId());
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.rvCategoryItem);
        final RecyclerView.LayoutManager recyclerLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerAdapter = new RVitemListAdapterAboutGoods(view.getContext());
        recyclerView.setAdapter(recyclerAdapter);


        Bundle bundle = getArguments();

        if (bundle != null) {
            presenter.getDataAboutGoods(bundle.getString(ID));
        }
        setUI();
        this.view = view;
        return view;
    }

    private void setUI() {

    }

    @Override
    public void setName(String name) {
        tvName.setText(name);
        try {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(name);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setPhoto(ArrayList<String> urles) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(view.getContext(), urles);
        viewPager.setAdapter(viewPagerAdapter);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @Override
    public void setDescription(String description) {
        tvDescription.setText(description);
    }

    @Override
    public void getDataForRecyclerView(String category) {
        presenter.getCategoryData(category);
    }

    @Override
    public void setPrice(String price) {
        tvPrice.setText(price);
    }

    @Override
    public void setFullDescription(String fullDescription) {
    }

    @Override
    public void setDataForRecyclerView(ArrayList<GoodsProperties> list) {
        recyclerAdapter.setData(list);
    }

    @Override
    public void setWishButton(boolean isInWish) {
        if (getView() != null) {
            if (!isInWish) {
                ivWish.setImageDrawable(getResources().getDrawable(R.drawable.heartoutlinebig));

            } else {
                ivWish.setImageDrawable(getResources().getDrawable(R.drawable.heart_black));
                Snackbar.make(getView(), "Додано у бажання", Snackbar.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    public void setBasketButton(boolean isInBasket) {
        if (getView() != null) {
            if (!isInBasket) {
                ivBasket.setImageDrawable(getResources().getDrawable(R.drawable.basketunfill));
            } else {
                ivBasket.setImageDrawable(getResources().getDrawable(R.drawable.basketfill));
                Snackbar.make(getView(), "Додано у корзинку", Snackbar.LENGTH_SHORT)
                        .show();
            }
        }

    }
}
