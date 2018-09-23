package com.artmurka.artmurkaapp.android.views.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Success
import com.artmurka.artmurkaapp.other.SaveDataFragment
import com.artmurka.artmurkaapp.presenter.adapters.RVcategoryAdapter
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ICategoryFragment
import com.google.gson.Gson


class FragmentCategoryChilds : Fragment(), ICategoryFragment {

    private var recyclerView: RecyclerView? = null
    private var recyclerAdapter: RVcategoryAdapter? = null
    private var dataFragment: SaveDataFragment? = null
    private var childs: List<Success>? = null

    val isOnline: Boolean
        get() {
            val cm = activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragment_category_childs, container, false)
        val fm = fragmentManager
        dataFragment = fm!!.findFragmentByTag("data") as SaveDataFragment

        if (dataFragment == null) {
            dataFragment = SaveDataFragment()
            fm.beginTransaction()
                    .add(dataFragment, "data")
                    .commit()
        }
        val bundle = arguments
        if (bundle!!.getParcelableArrayList<Parcelable>("childs") != null) {
            childs = bundle.getParcelableArrayList("childs")
        }
        if (bundle.getString("catName") != null) {
            setToolBarName(bundle.getString("catName")!!)
        }

        recyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView
        val recyclerLayoutManager = LinearLayoutManager(view.context)
        recyclerView!!.layoutManager = recyclerLayoutManager
        recyclerAdapter = RVcategoryAdapter(view.context)
        recyclerView!!.adapter = recyclerAdapter

        if (dataFragment!!.childs != null) {
            showCategories(dataFragment!!.childs)
        } else {
            if (isOnline) {
                showCategories(childs!!)
            } else {
                showError("Відсутній інтернет")
            }
        }
        return view

    }

    override fun showCategories(categoriesList: List<Success>) {
        dataFragment!!.childs = categoriesList
        recyclerAdapter!!.setData(categoriesList)
        Log.d("Log.d", "showCategories " + Gson().toJson(categoriesList))

    }

    override fun setToolBarName(name: String) {
        try {
            (activity as AppCompatActivity).supportActionBar!!.title = name
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }

    override fun showError(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
}// Required empty public constructor

