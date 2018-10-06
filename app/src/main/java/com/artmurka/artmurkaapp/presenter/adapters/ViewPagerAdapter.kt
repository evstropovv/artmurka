package com.artmurka.artmurkaapp.presenter.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.support.v4.view.ViewPager

import android.widget.ProgressBar
import android.widget.RelativeLayout

import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.activities.fullphoto.FullPhotoActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

import java.util.ArrayList


class ViewPagerAdapter(private val context: Context, val images: ArrayList<String>) : PagerAdapter() {

    override fun getCount(): Int {
        return images.size
    }

    override fun destroyItem(arg0: View, arg1: Int, arg2: Any) {
        (arg0 as ViewPager).removeView(arg2 as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }


    override fun saveState(): Parcelable? {
        return null
    }

    override fun instantiateItem(collection: View, position: Int): Any {
        val inflater = collection.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.image_pager_layout, null)
        (collection as ViewPager).addView(view)
        val img = view.findViewById<View>(R.id.img) as ImageView
        val progressBar = view.findViewById<View>(R.id.pbImage) as ProgressBar
        Picasso.with(context)
                .load(images[position])
                .placeholder(R.drawable.splash)
                .centerCrop()
                .fit()
                .into(img, object : Callback {
                    override fun onSuccess() {
                        progressBar.visibility = View.GONE
                    }

                    override fun onError() {

                    }
                })
        view.setOnClickListener { view ->
            val intent = Intent(view.context, FullPhotoActivity::class.java)
            val bundle = Bundle()
            bundle.putString("image", images[position])
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
        return view
    }

}