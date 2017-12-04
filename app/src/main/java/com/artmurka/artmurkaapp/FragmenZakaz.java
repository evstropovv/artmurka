package com.artmurka.artmurkaapp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmenZakaz extends Fragment {

    LinearLayout linearNovaPoshta, linerPikup;
    private TextView tvChoseAdress, tvPikup;
    private boolean npCheck = false;
    private CardView cardRegion, cardCity, cardPostOffice;

    public FragmenZakaz() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_zakaz, container, false);
        // Inflate the layout for this fragment
        linearNovaPoshta = (LinearLayout) view.findViewById(R.id.linearNovaPoshta);
        linerPikup = (LinearLayout) view.findViewById(R.id.linerPikup);
        tvChoseAdress = (TextView) view.findViewById(R.id.tvChoseAdress);
        tvPikup = (TextView) view.findViewById(R.id.tvPikup);
        cardRegion = (CardView) view.findViewById(R.id.cardRegion);
        cardCity = (CardView) view.findViewById(R.id.cardCity);
        cardPostOffice = (CardView) view.findViewById(R.id.cardPostOffice);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        linearNovaPoshta.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                npCheck = false;
                setCheckNP(npCheck);
            }
        });
        linerPikup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                npCheck = true;
                setCheckNP(npCheck);
            }
        });
    }

    void setCheckNP(boolean check) {
        if (!check) {
            linearNovaPoshta.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style_check));
            linerPikup.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style));
            tvChoseAdress.setVisibility(View.VISIBLE);
            tvPikup.setVisibility(View.GONE);
            cardRegion.setVisibility(View.VISIBLE);
            cardCity.setVisibility(View.VISIBLE);
            cardPostOffice.setVisibility(View.VISIBLE);

        } else {
            linearNovaPoshta.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style));
            linerPikup.setBackground(getResources().getDrawable(R.drawable.zakaz_frame_style_check));
            tvChoseAdress.setVisibility(View.GONE);
            tvPikup.setVisibility(View.VISIBLE);
            cardRegion.setVisibility(View.GONE);
            cardCity.setVisibility(View.GONE);
            cardPostOffice.setVisibility(View.GONE);
        }
    }
}
