package com.artmurka.artmurkaapp.android.views.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.artmurka.artmurkaapp.R


/**
 * A simple [Fragment] subclass.
 */
class DeliveryFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivery, container, false)
    }

    override fun onResume() {
        super.onResume()
        try {
            (activity as AppCompatActivity).supportActionBar!!.title = "Доставка та оплата"
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }
}// Required empty public constructor
