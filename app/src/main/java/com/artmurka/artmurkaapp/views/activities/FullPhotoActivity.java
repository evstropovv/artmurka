package com.artmurka.artmurkaapp.views.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.artmurka.artmurkaapp.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by Vasya on 11.02.2018.
 */

public class FullPhotoActivity extends AppCompatActivity {
    private PhotoView imageView;
    private ImageView ivClose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullphoto);
        imageView = (PhotoView) findViewById(R.id.zoomIV);
        ivClose = (ImageView) findViewById(R.id.ivClose);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Picasso.with(this)
                    .load(bundle.getString("image"))
                    .placeholder(R.drawable.splash)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {

                        }
                    });
        }
    }
}
