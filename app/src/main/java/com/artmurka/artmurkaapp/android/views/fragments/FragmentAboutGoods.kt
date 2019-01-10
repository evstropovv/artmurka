package com.artmurka.artmurkaapp.android.views.fragments

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.GoodsProperties
import com.artmurka.artmurkaapp.presenter.AboutGoodsPresenter
import com.artmurka.artmurkaapp.presenter.adapters.RVitemListAdapterAboutGoods
import com.artmurka.artmurkaapp.presenter.adapters.ViewPagerAdapter
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IFragmentAboutGoods
import com.artmurka.artmurkaapp.presenter.Presenter
import com.artmurka.artmurkaapp.presenter.PresenterView
import kotlinx.android.synthetic.main.fragment_fragment_about_goods.*

import java.util.ArrayList
import javax.inject.Inject


class FragmentAboutGoods : BaseFragment(), IFragmentAboutGoods, RVitemListAdapterAboutGoods.OnItemClickListener {

    override fun getLayout(): Int = R.layout.fragment_fragment_about_goods

    override fun getFragmentPresenter(): Presenter<out PresenterView> = presenter

    @Inject
    lateinit var presenter: AboutGoodsPresenter

    private val ID = "id"

    private var recyclerAdapter: RVitemListAdapterAboutGoods? = null


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter.takeView(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ivWish!!.setOnClickListener { v ->
            v.startAnimation(com.artmurka.artmurkaapp.other.RotateAnimation().getAnimation())
            presenter!!.btnClicked(v.id)
        }
        ivBasket!!.setOnClickListener { v ->

            v.startAnimation(com.artmurka.artmurkaapp.other.RotateAnimation().getAnimation())
            presenter!!.btnClicked(v.id)
        }
        val recyclerLayoutManager = LinearLayoutManager(view?.context, LinearLayoutManager.HORIZONTAL, false)
        rvCategoryItem.layoutManager = recyclerLayoutManager
        recyclerAdapter = RVitemListAdapterAboutGoods(view?.context!!)
        recyclerAdapter?.itemClickListener = this
        rvCategoryItem.adapter = recyclerAdapter


        val bundle = arguments

        if (bundle != null) {
            presenter!!.getDataAboutGoods(bundle.getString(ID))
        }
    }

    override fun toBasket(goodId: String) {
        presenter.toBasket(goodId)
    }

    override fun toWishList(goodsId: String) {
        presenter.toWishList(goodsId)
    }

    override fun setName(name: String) {
        tvName.text = name
        try {
            (activity as AppCompatActivity).supportActionBar!!.title = name
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }

    override fun setPhoto(urles: ArrayList<String>) {
        val viewPagerAdapter = ViewPagerAdapter(view!!.context, urles)


        viewPager!!.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager, true)
    }

    override fun setDescription(description: String) {
        tvDescription!!.text = description
    }

    override fun getDataForRecyclerView(category: String) {
        presenter!!.getCategoryData(category)
    }

    override fun setPrice(price: String) {
        tvPrice!!.text = price
    }

    override fun setArticle(art: String) {
        tvArcticle.text = art
    }

    override fun setFullDescription(fullDescription: String) {}

    override fun setDataForRecyclerView(list: ArrayList<GoodsProperties>) {
        recyclerAdapter!!.setData(list)
    }

    override fun setWishButton(isInWish: Boolean) {
        if (getView() != null) {
            if (!isInWish) {
                ivWish!!.setImageDrawable(resources.getDrawable(R.drawable.heartoutline))

            } else {
                ivWish!!.setImageDrawable(resources.getDrawable(R.drawable.heart_black))
                Snackbar.make(getView()!!, "Додано у бажання", Snackbar.LENGTH_SHORT)
                        .show()
            }
        }
    }

    override fun setBasketButton(isInBasket: Boolean) {
        if (getView() != null) {
            if (!isInBasket) {
                ivBasket!!.setImageDrawable(resources.getDrawable(R.drawable.basketunfill))
            } else {
                ivBasket!!.setImageDrawable(resources.getDrawable(R.drawable.basketfill))
                Snackbar.make(getView()!!, "Додано у корзинку", Snackbar.LENGTH_SHORT)
                        .show()
            }
        }

    }

}

