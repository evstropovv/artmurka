package com.artmurka.artmurkaapp.Views.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.artmurka.artmurkaapp.Other.ZoomImageView;
import com.artmurka.artmurkaapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Vasya on 11.02.2018.
 */

public class FullPhotoActivity extends AppCompatActivity {
    private ZoomImageView imageView;
    private ArrayList<String> images;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullphoto);
        imageView = (ZoomImageView) findViewById(R.id.zoomIV);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            Picasso.with(this)
                    .load(bundle.getString("image"))
                    .placeholder(R.drawable.splash)
                    .centerCrop()
                    .fit()
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            //progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {

                        }
                    });


        }



    }
}
