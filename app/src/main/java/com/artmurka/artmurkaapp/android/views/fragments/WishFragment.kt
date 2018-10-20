package com.artmurka.artmurkaapp.android.views.fragments


import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.wishList.GoodsListDescription
import com.artmurka.artmurkaapp.presenter.adapters.RVwishListAdapter
import com.artmurka.artmurkaapp.presenter.WishPresenter
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IWishFragment
import com.artmurka.artmurkaapp.presenter.Presenter
import com.artmurka.artmurkaapp.presenter.PresenterView
import kotlinx.android.synthetic.main.fragment_wish.*
import javax.inject.Inject


class WishFragment : BaseFragment(), IWishFragment {
    override fun getLayout(): Int = R.layout.fragment_wish

    override fun getFragmentPresenter(): Presenter<out PresenterView> =  presenter


    @Inject
    lateinit var presenter: WishPresenter
    private var recyclerAdapter: RVwishListAdapter? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter.takeView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wish, container, false)


        val recyclerLayoutManager = LinearLayoutManager(view.context)
        rvWish.layoutManager = recyclerLayoutManager
        recyclerAdapter = RVwishListAdapter(view.context)
        rvWish.adapter = recyclerAdapter

        presenter?.getDataForWishList()
        return view
    }


    override fun onResume() {
        super.onResume()
        try {
            (activity as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.fragment_wish_name)
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }

    override fun showWishList(list: List<GoodsListDescription>) {
        recyclerAdapter?.setData(list)
    }
}
