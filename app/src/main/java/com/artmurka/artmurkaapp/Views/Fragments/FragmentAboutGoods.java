package com.artmurka.artmurkaapp.Views.Fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.artmurka.artmurkaapp.Presenter.AboutGoodsPresenter;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.IAboutGoodsPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.IFragmentAboutGoods;
import com.squareup.picasso.Picasso;


public class FragmentAboutGoods extends Fragment implements IFragmentAboutGoods {

    IAboutGoodsPresenter presenter;
    private final String ID = "id";

    private TextView tvName, tvPrice, tvDescription;
    private ImageView ivPhoto;


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

        Bundle bundle = getArguments();

        if (bundle!=null){
            presenter.getDataAboutGoods(bundle.getString(ID));
        }
        setUI();
        return view;
    }

    private void setUI() {

    }

    @Override
    public void setName(String name) {
        tvName.setText(name);
    }

    @Override
    public void setPhoto(String url) {
        Picasso.with(getActivity()).load(url).into(ivPhoto);
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
