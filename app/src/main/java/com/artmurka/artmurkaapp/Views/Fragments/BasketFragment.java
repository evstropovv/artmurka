package com.artmurka.artmurkaapp.Views.Fragments;


import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemBasket.Item;
import com.artmurka.artmurkaapp.Presenter.Adapters.RVbasketAdapter;
import com.artmurka.artmurkaapp.Presenter.BasketPresenter;
import com.artmurka.artmurkaapp.Presenter.InterfacesPresenter.IBasketPresenter;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Activities.CheckoutActivity;
import com.artmurka.artmurkaapp.Views.Fragments.Interfaces.IBasketFragment;
import java.util.List;

public class BasketFragment extends Fragment implements IBasketFragment {
    private Button btnToMain, btnCheckout;
    private TextView tvMessage, tvPrice;

    private FrameLayout frameCheckout;

    private RecyclerView recyclerView;
    private RVbasketAdapter recyclerAdapter;
    private IBasketPresenter presenter;
    private LinearLayout linLayout;


    public BasketFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        frameCheckout = (FrameLayout) view.findViewById(R.id.frameCheckout);
        btnToMain = (Button) view.findViewById(R.id.btnToMain);
        tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        linLayout = (LinearLayout) view.findViewById(R.id.linLayout);
        btnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); //back button pressed.
            }
        });
        btnCheckout = (Button) view.findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CheckoutActivity.class);
                startActivity(intent);
            }
        });

        tvMessage = (TextView) view.findViewById(R.id.tvCartMessage);
        makeMessageInvisible(false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        final RecyclerView.LayoutManager recyclerLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerAdapter = new RVbasketAdapter(view.getContext(), this);
        recyclerView.setAdapter(recyclerAdapter);

        if (presenter == null) {
            presenter = new BasketPresenter(this);
        }
        presenter.getDataForbasket();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        try{
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Корзинка");
        }catch ( NullPointerException e){e.printStackTrace();}
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showItemsInBasket(List<Item> items) {
        recyclerAdapter.setData(items);
    }

    @Override
    public void makeMessageInvisible(boolean b) {
        if (b) {
            btnToMain.setVisibility(View.INVISIBLE);
            tvMessage.setVisibility(View.INVISIBLE);
            frameCheckout.setVisibility(View.VISIBLE);

        } else {
            btnToMain.setVisibility(View.VISIBLE);
            tvMessage.setVisibility(View.VISIBLE);
            frameCheckout.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public void showPrice(Integer price) {
        if (price > 0) {
            tvPrice.setText(price + " грн.");
        } else {
            tvPrice.setText(price + " грн.");
            btnCheckout.setVisibility(View.INVISIBLE);
            btnToMain.setVisibility(View.VISIBLE);
        }

    }
}
