package com.artmurka.artmurkaapp.android.views.fragments


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar

import com.artmurka.artmurkaapp.data.model.databases.Preferences
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist.GoodsProperties
import com.artmurka.artmurkaapp.presenter.adapters.RVitemListAdapterList
import com.artmurka.artmurkaapp.presenter.adapters.RVitemListGridAdapter
import com.artmurka.artmurkaapp.presenter.ItemListPresenter
import com.artmurka.artmurkaapp.presenter.adapters.RVitemListAdapter
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.IPresenterItemList
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.IItemListFragment
import com.artmurka.artmurkaapp.presenter.Presenter
import com.artmurka.artmurkaapp.presenter.PresenterView

import java.util.ArrayList
import javax.inject.Inject


class ItemListFragment : BaseFragment(), IItemListFragment {
    override fun getLayout(): Int = R.layout.fragment_item_list

    override fun getFragmentPresenter(): Presenter<out PresenterView> = presenter
    @Inject
    lateinit var presenter: ItemListPresenter
    private var recyclerView: RecyclerView? = null
    private var recyclerAdapter: RVitemListAdapter? = null
    private var recyclerAdapterList: RVitemListAdapterList? = null
    private var recyclerGridAdapter: RVitemListGridAdapter? = null
    private var url: String? = ""

    private var curPage = 1
    private var sort: String? = "name"
    private var order: String? = "asc"
    private val visibleThreshold = 4
    private var isLoading: Boolean? = false
    private var totalItemCount: Int = 0
    private var lastVisibleItem: Int = 0
    private var progressBar: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        progressBar = view.findViewById<View>(R.id.progressBar2) as ProgressBar
        Log.d("Log.d", "itemListFragment ")
        val bundle = arguments
        if (bundle != null) {
            if (bundle.getString("url") != null) {
                url = bundle.getString("url")
            }
            if (bundle.getString("sort") != null) {
                sort = bundle.getString("sort")
            }
            if (bundle.getString("order") != null) {
                order = bundle.getString("order")
            }
        }
        recyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView
        if (Preferences.listSettings == 1) {

            val recyclerLayoutManager = GridLayoutManager(view.context, 2)
            recyclerView!!.layoutManager = recyclerLayoutManager
            recyclerAdapter = RVitemListAdapter(view.context)
            recyclerView!!.adapter = recyclerAdapter

            recyclerView!!.setOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }

                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    presenter!!.getCategoriesData(++curPage, url!!, sort!!, order!!)
                }
            })

        } else if (Preferences.listSettings == 2) {
            val recyclerLayoutManager = LinearLayoutManager(view.context)
            recyclerLayoutManager.orientation = LinearLayout.VERTICAL
            recyclerView!!.layoutManager = recyclerLayoutManager
            recyclerAdapterList = RVitemListAdapterList(view.context)
            recyclerView!!.adapter = recyclerAdapterList

            recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    totalItemCount = recyclerLayoutManager.itemCount
                    Log.d("Log.d", totalItemCount.toString() + "")
                    lastVisibleItem = recyclerLayoutManager.findLastVisibleItemPosition()
                    Log.d("Log.d", lastVisibleItem.toString() + "")
                    if ((!isLoading!!)!! && totalItemCount <= lastVisibleItem + visibleThreshold) {

                        presenter!!.getCategoriesData(++curPage, url!!, sort!!, order!!)
                        progressBar!!.visibility = View.VISIBLE
                        isLoading = true
                    }
                }
            })
        } else if (Preferences.listSettings == 3) {

            val recyclerLayoutManager = StaggeredGridLayoutManager(3,
                    StaggeredGridLayoutManager.VERTICAL)
            recyclerView!!.layoutManager = recyclerLayoutManager
            recyclerGridAdapter = RVitemListGridAdapter(view.context)
            recyclerView!!.adapter = recyclerGridAdapter

            recyclerView!!.setOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }

                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    presenter!!.getCategoriesData(++curPage, url!!, sort!!, order!!)
                }
            })
        }

        //TODO
         Preferences.listUrl = this!!.url!!


        presenter!!.getCategoriesData(++curPage, url!!, sort!!, order!!)

        progressBar!!.visibility = View.VISIBLE
        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true) //for menu
    }


    override fun showItemList(goodsProperties: ArrayList<GoodsProperties>) {
        when (Preferences.listSettings) {
            1 -> recyclerAdapter!!.setData(goodsProperties)
            2 -> recyclerAdapterList!!.setData(goodsProperties)
            3 -> recyclerGridAdapter!!.setData(goodsProperties)
            else -> {
            }
        }
        progressBar!!.visibility = View.INVISIBLE
        isLoading = false
    }

    override fun showError(error: String) {
        Snackbar.make(view!!, error, Snackbar.LENGTH_LONG)
                .setAction(resources.getString(R.string.any_error)) { }.show()
        progressBar!!.visibility = View.INVISIBLE
    }

    override fun setTitle(title: String) {
        try {
            (activity as AppCompatActivity).supportActionBar!!.title = title

        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }

    override fun stopProgressBar() {
        progressBar!!.visibility = View.INVISIBLE
    }


    override fun onPrepareOptionsMenu(menu: Menu?) {
        menu!!.findItem(R.id.sort).isVisible = true
        super.onPrepareOptionsMenu(menu)
    }
}
