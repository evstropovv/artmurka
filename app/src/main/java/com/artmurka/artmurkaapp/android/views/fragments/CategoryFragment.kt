package com.artmurka.artmurkaapp.android.views.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Success
import com.artmurka.artmurkaapp.other.SaveDataFragment
import com.artmurka.artmurkaapp.presenter.adapters.RVcategoryAdapter
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.presenter.*
import com.artmurka.artmurkaapp.android.views.activities.main.MainActivity
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ICategoryFragment
import com.artmurka.artmurkaapp.other.NetworkCheckUtil
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_category.*


class CategoryFragment : BaseFragment(), ICategoryFragment {

    @Inject
    lateinit var presenter: CategoryPresenter

    @Inject
    lateinit var onlineCheck: NetworkCheckUtil

    override fun getLayout(): Int = R.layout.fragment_category
    override fun getFragmentPresenter(): Presenter<out PresenterView> = presenter

    private var recyclerAdapter: RVcategoryAdapter? = null
    private var dataFragment: SaveDataFragment? = null


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter.takeView(this)
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataFragment = fragmentManager?.findFragmentByTag("data") as SaveDataFragment?

        if (dataFragment == null) {
            dataFragment = SaveDataFragment()
            fragmentManager?.beginTransaction()?.add(dataFragment!!, "data")
                    ?.commit()
        }
        btnCall.setOnClickListener {
            presenter.makeCall()
        }

        val recyclerLayoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = recyclerLayoutManager
        recyclerAdapter = RVcategoryAdapter(context!!)
        recyclerView.adapter = recyclerAdapter


        if (dataFragment?.categories != null) {
            showCategories(dataFragment?.categories!!)

        } else {
            if (!onlineCheck.isOnline) {
                showError("Відсутній інтернет. Перезавантажити ?")
            } else {
                presenter!!.getCategoriesData(true)
                progressBar2.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            (activity as AppCompatActivity).supportActionBar!!.title = "Каталог товарів"
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun showCategories(categoriesList: List<Success>) {
        dataFragment!!.categories = categoriesList
        recyclerAdapter!!.setData(categoriesList)
        progressBar2.visibility = View.INVISIBLE
    }

    override fun setToolBarName(name: String) {}

    override fun showError(error: String) {
        if (view != null) {
            Snackbar.make(view!!, error, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Так") {
                        startActivity(Intent(context, MainActivity::class.java))
                        activity!!.finish()
                    }.show()
        }
        progressBar2.visibility = View.INVISIBLE
    }
}
