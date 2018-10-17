package com.artmurka.artmurkaapp.android.views.fragments


import android.app.FragmentManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket.Item
import com.artmurka.artmurkaapp.presenter.Presenter
import com.artmurka.artmurkaapp.presenter.PresenterView
import com.artmurka.artmurkaapp.presenter.adapters.RVbasketAdapter
import com.artmurka.artmurkaapp.presenter.BasketPresenter
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.activities.checkout.CheckoutActivity
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IBasketFragment
import kotlinx.android.synthetic.main.fragment_cart.*

import javax.inject.Inject

class BasketFragment : BaseFragment(), IBasketFragment, RVbasketAdapter.OnItemClickListener {


    private var recyclerAdapter: RVbasketAdapter? = null

    @Inject
    lateinit var presenter: BasketPresenter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter.takeView(this)
    }

    override fun onRefreshItem(position: String, id: String) {
        presenter.onRefreshItem(position, id)
    }

    override fun addToWishList(id: String) {
        presenter.addToWishList(id)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnToMain.setOnClickListener {
            fragmentManager!!.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE) //back button pressed.
        }
        btnCheckout!!.setOnClickListener {
            val intent = Intent(context, CheckoutActivity::class.java)
            startActivity(intent)
        }
        makeMessageInvisible(false)
        val recyclerLayoutManager = LinearLayoutManager(view?.context)
        recyclerView!!.layoutManager = recyclerLayoutManager
        recyclerAdapter = RVbasketAdapter(view?.context!!, this)
        recyclerView!!.adapter = recyclerAdapter
        recyclerAdapter?.onItemClickListener = this
        presenter!!.getDataForbasket()
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
            btnToMain.visibility = View.INVISIBLE
            tvCartMessage.visibility = View.INVISIBLE
            frameCheckout!!.visibility = View.VISIBLE

        } else {
            btnToMain.visibility = View.VISIBLE
            tvCartMessage.visibility = View.VISIBLE
            frameCheckout.visibility = View.INVISIBLE

        }
    }

    override fun showPrice(price: String) {
        tvPrice.text = "$price грн."
        if (java.lang.Float.parseFloat(price) < 0) {
            btnCheckout.visibility = View.INVISIBLE
            btnToMain.visibility = View.VISIBLE
        }
    }


    override fun showItemsInBasket(items: List<Item>) {
        recyclerAdapter?.setData(items)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_cart
    }

    override fun getFragmentPresenter(): Presenter<out PresenterView> = presenter
}
