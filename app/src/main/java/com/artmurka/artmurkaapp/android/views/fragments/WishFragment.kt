package com.artmurka.artmurkaapp.android.views.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription
import com.artmurka.artmurkaapp.presenter.adapters.RVwishListAdapter
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IWishPresenter
import com.artmurka.artmurkaapp.presenter.WishPresenter
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IWishFragment
import com.artmurka.artmurkaapp.presenter.Presenter
import com.artmurka.artmurkaapp.presenter.PresenterView
import javax.inject.Inject


class WishFragment : BaseFragment(), IWishFragment {
    override fun getLayout(): Int = R.layout.fragment_wish

    override fun getFragmentPresenter(): Presenter<out PresenterView> =  presenter


    @Inject
    lateinit var presenter: WishPresenter
    private var recyclerView: RecyclerView? = null
    private var recyclerAdapter: RVwishListAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wish, container, false)

        recyclerView = view.findViewById<View>(R.id.rvWish) as RecyclerView
        val recyclerLayoutManager = LinearLayoutManager(view.context)
        recyclerView!!.layoutManager = recyclerLayoutManager
        recyclerAdapter = RVwishListAdapter(view.context)
        recyclerView!!.adapter = recyclerAdapter

        presenter!!.getDataForWishList()
        return view
    }


    override fun onResume() {
        super.onResume()
        try {
            (activity as AppCompatActivity).supportActionBar!!.title = "Мої бажання"
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }

    override fun showWishList(list: List<GoodsListDescription>) {
        recyclerAdapter!!.setData(list)
    }
}
