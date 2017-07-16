package com.artmurka.artmurkaapp.Views.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import com.artmurka.artmurkaapp.Presenter.AboutGoodsPresenter;
import com.artmurka.artmurkaapp.Presenter.Adapters.ViewPagerAdapter;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.IAboutGoodsPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.IFragmentAboutGoods;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class FragmentAboutGoods extends Fragment implements IFragmentAboutGoods {

    IAboutGoodsPresenter presenter;
    private final String ID = "id";

    private TextView tvName, tvPrice, tvDescription;
    private ImageView ivPhoto;
    private ViewPager viewPager;
    private View view;


    public FragmentAboutGoods() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_about_goods, container, false);
        if (presenter==null){
            presenter = new AboutGoodsPresenter(this);
        }
        tvName = (TextView)view.findViewById(R.id.tvName);
        tvPrice = (TextView)view.findViewById(R.id.tvPrice);
        tvDescription = (TextView)view.findViewById(R.id.tvDescription);
        ivPhoto = (ImageView)view.findViewById(R.id.itemPhoto);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        Bundle bundle = getArguments();

        if (bundle!=null){
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
    }

    @Override
    public void setPhoto(ArrayList<String> urles) {
      //  Picasso.with(getActivity()).load(url).into(ivPhoto);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(view.getContext(), urles);
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void setDescription(String description) {
      tvDescription.setText(description);
    }

    @Override
    public void setPrice(String price) {
        tvPrice.setText(price);
    }

    @Override
    public void setFullDescription(String fullDescription) {
    }

    @Override
    public void setDataForRecyclerView() {
    }
}
