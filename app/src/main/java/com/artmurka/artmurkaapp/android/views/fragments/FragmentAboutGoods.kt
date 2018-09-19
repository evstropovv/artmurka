package com.artmurka.artmurkaapp.android.views.fragments

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView

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


class FragmentAboutGoods : BaseFragment(), IFragmentAboutGoods {
    override fun getLayout(): Int = R.layout.fragment_fragment_about_goods

    override fun getFragmentPresenter(): Presenter<out PresenterView> = presenter

    @Inject
    lateinit var presenter: AboutGoodsPresenter
    private val ID = "id"

    private var tvName: TextView? = null
    private var tvPrice: TextView? = null
    private var tvDescription: TextView? = null
    private var ivPhoto: ImageView? = null
    private var ivWish: ImageView? = null
    private var ivBasket: ImageView? = null
    private var viewPager: ViewPager? = null
    private val cardView: CardView? = null
    private var recyclerView: RecyclerView? = null
    private var recyclerAdapter: RVitemListAdapterAboutGoods? = null


    override fun onAttach(context: Context?) {
        presenter!!.takeView(this)
        super.onAttach(context)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View?{
    // Inflate the layout for this fragment
        val fragment = inflater.inflate(R.layout.fragment_fragment_about_goods, container, false)

        tvName = fragment.findViewById<View>(R.id.tvName) as TextView
        tvPrice = fragment.findViewById<View>(R.id.tvPrice) as TextView
        tvDescription = fragment.findViewById<View>(R.id.tvDescription) as TextView
        ivPhoto = fragment.findViewById<View>(R.id.ivItemPhoto) as ImageView
        viewPager = fragment.findViewById<View>(R.id.viewPager) as ViewPager

        ivWish = fragment.findViewById<View>(R.id.ivWish) as ImageView
        ivWish!!.setOnClickListener { v ->
            val anim = RotateAnimation(-10f, 10f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            anim.interpolator = LinearInterpolator()
            anim.repeatCount = Animation.RELATIVE_TO_PARENT
            anim.duration = 100
            v.startAnimation(anim)

            presenter!!.btnClicked(v.id)
        }
        ivBasket = fragment.findViewById<View>(R.id.ivBasket) as ImageView
        ivBasket!!.setOnClickListener { v ->
            val anim = RotateAnimation(-10f, 10f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            anim.interpolator = LinearInterpolator()
            anim.repeatCount = Animation.RELATIVE_TO_PARENT
            anim.duration = 100
            v.startAnimation(anim)
            presenter!!.btnClicked(v.id)
        }

        recyclerView = fragment.findViewById<View>(R.id.rvCategoryItem) as RecyclerView
        val recyclerLayoutManager = LinearLayoutManager(fragment.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView!!.layoutManager = recyclerLayoutManager
        recyclerAdapter = RVitemListAdapterAboutGoods(fragment.context)
        recyclerView!!.adapter = recyclerAdapter


        val bundle = arguments

        if (bundle != null) {
            presenter!!.getDataAboutGoods(bundle.getString(ID))
        }
        setUI()
        return view
    }

    private fun setUI() {

    }

    override fun setName(name: String) {
        tvName!!.text = name
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

}// Required empty public constructor

