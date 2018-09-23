package com.artmurka.artmurkaapp.presenter.adapters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.artmurka.artmurkaapp.android.views.fragments.FragmentAboutGoods
import com.artmurka.artmurkaapp.android.views.fragments.FragmentDescriptionGoods


class PagerAdapter(fm: FragmentManager, private val numberOfTabs: Int, private val currentId: String) : FragmentStatePagerAdapter(fm) {
    private val bundle: Bundle

    init {
        bundle = Bundle()
        bundle.putString("id", currentId)
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                val fragmentAboutGoods = FragmentAboutGoods()
                fragmentAboutGoods.arguments = bundle
                return fragmentAboutGoods
            }
            1 -> {
                val fragmentDescriptionGoods = FragmentDescriptionGoods()
                fragmentDescriptionGoods.arguments = bundle
                return fragmentDescriptionGoods
            }
            else -> return null
        }
    }

    override fun getCount(): Int {
        return numberOfTabs
    }
}
