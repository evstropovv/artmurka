package com.artmurka.artmurkaapp.android.views.fragments


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.artmurka.artmurkaapp.data.model.databases.Preferences
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.orders.Orders
import com.artmurka.artmurkaapp.presenter.adapters.RVorderListAdapter
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IOrderPresenter
import com.artmurka.artmurkaapp.presenter.OrdersPresenter
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IOrderFragment
import com.artmurka.artmurkaapp.presenter.Presenter
import com.artmurka.artmurkaapp.presenter.PresenterView
import javax.inject.Inject

class OrderFragment : BaseFragment(), IOrderFragment {

    @Inject
    lateinit var presenter: OrdersPresenter

    override fun getLayout(): Int = R.layout.fragment_order

    override fun getFragmentPresenter(): Presenter<out PresenterView> = presenter

    //фрагмент страницы заказов


    private var rvOrder: RecyclerView? = null
    private var adapter: RVorderListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_order, container, false)
        rvOrder = view.findViewById<View>(R.id.rvOrder) as RecyclerView
        val recyclerLayoutManager = LinearLayoutManager(view.context)
        rvOrder!!.layoutManager = recyclerLayoutManager
        adapter = RVorderListAdapter(view.context)
        rvOrder!!.adapter = adapter

        if (Preferences.isLogin!!) {
            presenter.getOrders()
        } else {
            val snackbar = Snackbar.make(view, resources.getString(R.string.fragment_order_status_toast_message), Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
            snackbar.duration = 8000 // 8 секунд
            snackbar.show()
        }

        return view
    }

    override fun showOrders(list: Orders) {
        adapter!!.setData(list)
    }

    override fun onResume() {
        super.onResume()
        try {
            (activity as AppCompatActivity).supportActionBar!!.title = resources.getString(R.string.fragment_order_status_my_orders)
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }
}
