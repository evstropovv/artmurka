package com.artmurka.artmurkaapp.android.views.fragments


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.artmurka.artmurkaapp.data.model.databases.Preferences
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.orders.Orders
import com.artmurka.artmurkaapp.presenter.adapters.RVorderListAdapter
import com.artmurka.artmurkaapp.presenter.OrdersPresenter
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IOrderFragment
import com.artmurka.artmurkaapp.presenter.Presenter
import com.artmurka.artmurkaapp.presenter.PresenterView
import kotlinx.android.synthetic.main.fragment_order.*
import javax.inject.Inject
//фрагмент страницы заказов
class OrderFragment : BaseFragment(), IOrderFragment {
    override fun showError(msg: String) {

    }


    @Inject
    lateinit var presenter: OrdersPresenter

    private var adapter: RVorderListAdapter? = null

    override fun getLayout(): Int = R.layout.fragment_order

    override fun getFragmentPresenter(): Presenter<out PresenterView> = presenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvOrder.layoutManager = LinearLayoutManager( context)
        adapter = RVorderListAdapter( context!!)
        rvOrder.adapter = adapter

        if (Preferences.isLogin!!) {
            presenter.getOrders()
        } else {
            val snackbar = Snackbar.make(view!!, resources.getString(R.string.fragment_order_status_toast_message), Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
            snackbar.duration = 8000 // 8 секунд
            snackbar.show()
        }
    }

    override fun showOrders(list: Orders) {
        adapter?.setData(list)
    }
    override fun showProgress() {

    }

    override fun hideProgress() {

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
