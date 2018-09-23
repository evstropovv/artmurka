package com.artmurka.artmurkaapp.android.views.fragments


import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IAboutGoodsPresenter
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IDescriptionGoods
import com.artmurka.artmurkaapp.presenter.AboutGoodsPresenter
import com.artmurka.artmurkaapp.presenter.DescriptionGoodsPresenter
import com.artmurka.artmurkaapp.presenter.Presenter
import com.artmurka.artmurkaapp.presenter.PresenterView
import javax.inject.Inject


class FragmentDescriptionGoods : BaseFragment(), IDescriptionGoods {

    @Inject
    lateinit var presenter: DescriptionGoodsPresenter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter.takeView(this)
    }

    override fun getLayout(): Int = R.layout.fragment_fragment_description_goods

    override fun getFragmentPresenter(): Presenter<out PresenterView> = presenter


}
