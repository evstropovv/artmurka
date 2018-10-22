package com.artmurka.artmurkaapp.android.views.fragments

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription
import com.artmurka.artmurkaapp.presenter.adapters.RVwishListAdapter
import com.artmurka.artmurkaapp.presenter.WishPresenter
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IWishFragment
import com.artmurka.artmurkaapp.presenter.Presenter
import com.artmurka.artmurkaapp.presenter.PresenterView
import kotlinx.android.synthetic.main.fragment_wish.*
import javax.inject.Inject


class WishFragment : BaseFragment(), IWishFragment, RVwishListAdapter.OnItemClickListener {


    @Inject
    lateinit var presenter: WishPresenter

    private var recyclerAdapter: RVwishListAdapter? = null

    override fun getLayout(): Int = R.layout.fragment_wish

    override fun getFragmentPresenter(): Presenter<out PresenterView> = presenter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter.takeView(this)
    }

    override fun deleteFromWishOnline(goodId: String) {
        presenter.deleteFromWishOnline(goodId)
    }

    override fun toBasket(goodId: String) {
        presenter.toBasket(goodId)
    }

//    override fun deleteFromWisList(goodId: String) {
//        recyclerAdapter?.deleteFromWishOnline(goodId)
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvWish.layoutManager = LinearLayoutManager(context)
        recyclerAdapter = RVwishListAdapter(context!!)
        recyclerAdapter?.clickListener = this
        rvWish.adapter = recyclerAdapter
        presenter.getDataForWishList()
    }


    override fun onResume() {
        super.onResume()
        try {
            (activity as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.fragment_wish_name)
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }

    override fun showWishList(list: MutableList<GoodsListDescription>) {
        recyclerAdapter?.setData(list)
    }
}
