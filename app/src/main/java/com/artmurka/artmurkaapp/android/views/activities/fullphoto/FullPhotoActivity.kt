package com.artmurka.artmurkaapp.android.views.activities.fullphoto


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.artmurka.artmurkaapp.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_fullphoto.*

/**
 * Created by Vasya on 11.02.2018.
 */

class FullPhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullphoto)
        ivClose.setOnClickListener { finish() }
        val bundle = intent.extras
        if (bundle != null) {
            Picasso.with(this)
                    .load(bundle.getString("image"))
                    .placeholder(R.drawable.splash)
                    .into(zoomIV, object : Callback {
                        override fun onSuccess() {}
                        override fun onError() {}
                    })
        }
    }
}
