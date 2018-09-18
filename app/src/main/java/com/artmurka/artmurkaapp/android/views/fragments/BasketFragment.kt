package com.artmurka.artmurkaapp.android.views.fragments


import android.app.FragmentManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.Item
import com.artmurka.artmurkaapp.presenter.Presenter
import com.artmurka.artmurkaapp.presenter.PresenterView
import com.artmurka.artmurkaapp.presenter.adapters.RVbasketAdapter
import com.artmurka.artmurkaapp.presenter.BasketPresenter
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IBasketPresenter
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.activities.checkout.CheckoutActivity
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IBasketFragment

import javax.inject.Inject

class BasketFragment : BaseFragment(), IBasketFragment {
    private var btnToMain: Button? = null
    private var btnCheckout: Button? = null
    private var tvMessage: TextView? = null
    private var tvPrice: TextView? = null

    private var frameCheckout: FrameLayout? = null

    private var recyclerView: RecyclerView? = null
    private var recyclerAdapter: RVbasketAdapter? = null

    private var linLayout: LinearLayout? = null

    @Inject
    lateinit var presenter: BasketPresenter

    override fun onAttach(context: Context?) {
        presenter!!.takeView(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        frameCheckout = view.findViewById<View>(R.id.frameCheckout) as FrameLayout
        btnToMain = view.findViewById<View>(R.id.btnToMain) as Button
        tvPrice = view.findViewById<View>(R.id.tvPrice) as TextView
        linLayout = view.findViewById<View>(R.id.linLayout) as LinearLayout
        btnToMain!!.setOnClickListener {
            fragmentManager!!.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE) //back button pressed.
        }
        btnCheckout = view.findViewById<View>(R.id.btnCheckout) as Button
        btnCheckout!!.setOnClickListener {
            val intent = Intent(context, CheckoutActivity::class.java)
            startActivity(intent)
        }

        tvMessage = view.findViewById<View>(R.id.tvCartMessage) as TextView
        makeMessageInvisible(false)
        recyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView
        val recyclerLayoutManager = LinearLayoutManager(view.context)
        recyclerView!!.layoutManager = recyclerLayoutManager
        recyclerAdapter = RVbasketAdapter(view.context, this)
        recyclerView!!.adapter = recyclerAdapter

        presenter!!.getDataForbasket()


        return view
    }

    override fun onResume() {
        super.onResume()
        try {
            (activity as AppCompatActivity).supportActionBar!!.title = "Корзинка"
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        // presenter.onDetach();
    }

    override fun showError(error: String) {

    }


    override fun makeMessageInvisible(b: Boolean) {
        if (b) {
            btnToMain!!.visibility = View.INVISIBLE
            tvMessage!!.visibility = View.INVISIBLE
            frameCheckout!!.visibility = View.VISIBLE

        } else {
            btnToMain!!.visibility = View.VISIBLE
            tvMessage!!.visibility = View.VISIBLE
            frameCheckout!!.visibility = View.INVISIBLE

        }
    }

    override fun showPrice(price: String) {
        tvPrice!!.text = "$price грн."
        if (java.lang.Float.parseFloat(price) < 0) {
            btnCheckout!!.visibility = View.INVISIBLE
            btnToMain!!.visibility = View.VISIBLE
        }
    }


    override fun showItemsInBasket(items: List<Item>) {
        recyclerAdapter!!.setData(items)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_cart
    }

    override fun getFragmentPresenter(): Presenter<out PresenterView> = presenter
}
