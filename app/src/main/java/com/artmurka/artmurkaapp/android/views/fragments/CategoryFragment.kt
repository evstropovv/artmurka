package com.artmurka.artmurkaapp.android.views.fragments

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Success

import com.artmurka.artmurkaapp.other.Const
import com.artmurka.artmurkaapp.other.SaveDataFragment
import com.artmurka.artmurkaapp.presenter.adapters.RVcategoryAdapter
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.presenter.*
import com.artmurka.artmurkaapp.android.views.activities.main.MainActivity
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ICategoryFragment
import javax.inject.Inject


class CategoryFragment : BaseFragment(), ICategoryFragment {

    @Inject
    lateinit var presenter: CategoryPresenter

    override fun getLayout(): Int = R.layout.fragment_category
    override fun getFragmentPresenter(): Presenter<out PresenterView> = presenter

    private var recyclerView: RecyclerView? = null
    private var recyclerAdapter: RVcategoryAdapter? = null
    private var btnCall: Button? = null
    private var dataFragment: SaveDataFragment? = null
    private var progressBar: ProgressBar? = null

    val isOnline: Boolean
        get() {
            val cm = activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter.takeView(this)
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)
        dataFragment =  fragmentManager?.findFragmentByTag("data")  as SaveDataFragment?
        progressBar = view.findViewById<View>(R.id.progressBar2) as ProgressBar

        if (dataFragment == null) {
            dataFragment = SaveDataFragment()
            fragmentManager?.beginTransaction()?.add(dataFragment, "data")
                    ?.commit()
        }

        btnCall = view.findViewById<View>(R.id.btnCall) as Button
        btnCall!!.setOnClickListener {
            val call = Uri.parse("tel:" + Const.TEL_NUMBER)
            val surf = Intent(Intent.ACTION_DIAL, call)
            startActivity(surf)
        }


        recyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView
//        val recyclerLayoutManager = LinearLayoutManager(view.context)
        val recyclerLayoutManager = GridLayoutManager(view.context, 2)
        recyclerView!!.layoutManager = recyclerLayoutManager
        recyclerAdapter = RVcategoryAdapter(view.context)
        recyclerView!!.adapter = recyclerAdapter


        if (dataFragment!!.categories != null) {
            showCategories(dataFragment!!.categories)

        } else {
            if (!isOnline) {
                showError("Відсутній інтернет. Перезавантажити ?")
            } else {
                presenter!!.getCategoriesData(true)
                progressBar!!.visibility = View.VISIBLE
            }
        }
        return view
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
        progressBar!!.visibility = View.INVISIBLE
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
        progressBar!!.visibility = View.INVISIBLE
    }

}
