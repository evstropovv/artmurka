package com.artmurka.artmurkaapp.Views.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.artmurka.artmurkaapp.Other.Const;
import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.Views.Activities.MainActivity;

public class BasketFragment extends Fragment {
    private Button btnToMain;
    private TextView tvMessage;

    public BasketFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        btnToMain = (Button) view.findViewById(R.id.btnToMain);
        btnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack(); //нажатие кнопки назад
            }
        });
        tvMessage = (TextView) view.findViewById(R.id.tvCartMessage);
        return view;
    }

}
