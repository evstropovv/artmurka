package com.artmurka.artmurkaapp.presenter.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.support.v4.view.ViewPager;

import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.artmurka.artmurkaapp.R;
import com.artmurka.artmurkaapp.views.activities.FullPhotoActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<String> images = new ArrayList<>();

    public ViewPagerAdapter(Context context, ArrayList<String> IMAGES) {
        this.images = IMAGES;
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }


    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public Object instantiateItem(View collection, int position) {
        LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.image_pager_layout, null);
        ((ViewPager) collection).addView(view);
        final ImageView img = (ImageView) view.findViewById(R.id.img);
        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.pbImage);
        Picasso.with(context)
                .load(images.get(position))
                .placeholder(R.drawable.splash)
                .centerCrop()
                .fit()
                .into(img, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FullPhotoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("image", images.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return view;
    }

}