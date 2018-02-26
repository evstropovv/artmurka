package com.artmurka.artmurkaapp;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.artmurka.artmurkaapp.Other.ZoomImageView;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by Vasya on 11.02.2018.
 */

public class DialogZoomView extends DialogFragment {

    private ImageView imageView;
    private PhotoViewAttacher photoViewAttacher;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_fullphoto, null);
        v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        imageView = (ImageView) v.findViewById(R.id.zoomIV);
        photoViewAttacher = new PhotoViewAttacher(imageView);
        Picasso.with(v.getContext())
                .load(getArguments().getString("image"))
                .placeholder(R.drawable.splash)
                .centerCrop()
                .fit()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        //progressBar.setVisibility(View.GONE);
                        photoViewAttacher.update();
                    }

                    @Override
                    public void onError() {

                    }
                });

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}