package com.artmurka.artmurkaapp.android.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artmurka.artmurkaapp.model.pojo.itemlist.wishList.GoodsListDescription;
import com.artmurka.artmurkaapp.presenter.adapters.RVwishListAdapter;
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IWishPresenter;
import com.artmurka.artmurkaapp.presenter.WishPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IWishFragment;

import java.util.List;



public class WishFragment extends Fragment implements IWishFragment {
//фрагмент страницы желаний
    private IWishPresenter presenter;
    private RecyclerView recyclerView;
    private RVwishListAdapter recyclerAdapter;


    public WishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wish, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rvWish);
        final RecyclerView.LayoutManager recyclerLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerAdapter = new RVwishListAdapter(view.getContext());
        recyclerView.setAdapter(recyclerAdapter);

        if (presenter ==null) presenter = new WishPresenter(this);
        presenter.getDataForWishList();
        return view;
    }

    @Override
    public void showWishList(List<GoodsListDescription> list) {
        recyclerAdapter.setData(list);
    }

    @Override
    public void onResume() {
        super.onResume();
        try{
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Мої бажання");
        }catch ( NullPointerException e){e.printStackTrace();}

    }
}
