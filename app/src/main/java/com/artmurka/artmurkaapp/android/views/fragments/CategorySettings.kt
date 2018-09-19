package com.artmurka.artmurkaapp.android.views.fragments


import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast

import com.artmurka.artmurkaapp.data.model.databases.Preferences
import com.artmurka.artmurkaapp.presenter.CategorySettingsPresenter
import com.artmurka.artmurkaapp.presenter.Presenter
import com.artmurka.artmurkaapp.presenter.PresenterView
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.ICategorySettingsPresenter
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ICategorySettings

import java.util.HashMap
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class CategorySettings : BaseFragment(), ICategorySettings {

    @Inject
    lateinit var presenter: CategorySettingsPresenter

    private var ivMicrosoft: ImageView? = null
    private var ivList: ImageView? = null
    private var ivGrid: ImageView? = null
    private var btnApply: Button? = null
    private var radioGroup: RadioGroup? = null

    private val spinnerDate: Spinner? = null
    private val spinnerPrice: Spinner? = null
    private var currentSelect: Int? = 2

    override fun getLayout(): Int {
        return R.layout.fragment_category_settings
    }

    override fun getFragmentPresenter(): Presenter<out PresenterView> = presenter

    override fun onAttach(context: Context?) {
        presenter?.takeView(this)
        super.onAttach(context)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_category_settings, container, false)

        btnApply = view.findViewById<View>(R.id.btnApply) as Button
        ivList = view.findViewById<View>(R.id.ivList) as ImageView
        ivMicrosoft = view.findViewById<View>(R.id.ivMicrosoft) as ImageView
        ivGrid = view.findViewById<View>(R.id.ivGrid) as ImageView
        radioGroup = view.findViewById<View>(R.id.radioGroup) as RadioGroup
        radioGroup!!.check(Preferences.checkedRadioButton!!)
        setIvListener()
        changeIconsColor(Preferences.listSettings!!)
        btnApply!!.setOnClickListener { view ->
            val settings = HashMap<String, String>()
            Preferences.checkedRadioButton = radioGroup!!.checkedRadioButtonId
            when (radioGroup!!.checkedRadioButtonId) {
                R.id.rbLowCost -> {
                    Toast.makeText(view.context, "Дешево!", Toast.LENGTH_SHORT).show()
                    settings["sort"] = "price"
                    settings["order"] = "ask"
                }
                R.id.rbExpensive -> {
                    Toast.makeText(view.context, "дорого!", Toast.LENGTH_SHORT).show()
                    settings["sort"] = "price"
                    settings["order"] = "desc"
                }
                R.id.rbName -> {
                    Toast.makeText(view.context, "По имени!", Toast.LENGTH_SHORT).show()
                    settings["sort"] = "name"
                    settings["order"] = "ask"
                }
                R.id.rvNew -> {
                    Toast.makeText(view.context, "Новые!", Toast.LENGTH_SHORT).show()
                    settings["sort"] = "added_time"
                    settings["order"] = "ask"
                }
                R.id.rbOld -> {
                    Toast.makeText(view.context, "Старые!", Toast.LENGTH_SHORT).show()
                    settings["sort"] = "added_time"
                    settings["order"] = "desc"
                }
                else -> {
                    Toast.makeText(view.context, "По имени!", Toast.LENGTH_SHORT).show()
                    settings["sort"] = "name"
                    settings["order"] = "ask"
                }
            }
            presenter!!.applyChanges(settings, currentSelect!!)
            Preferences.listSettings = currentSelect
            val fm = activity!!.supportFragmentManager
            val itemList = ItemListFragment()
            val bundle = Bundle()
            bundle.putString("url", Preferences.listUrl)
            bundle.putString("sort", settings["sort"])
            bundle.putString("order", settings["order"])
            bundle.putInt("list", currentSelect!!)
            itemList.arguments = bundle
            fm.beginTransaction()
                    .replace(R.id.mainFrame, itemList)
                    .addToBackStack(null)
                    .commit()
            fm.executePendingTransactions()
        }
        return view
    }

    private fun setIvListener() {

        ivList!!.setOnClickListener { changeIconsColor(1) }

        ivMicrosoft!!.setOnClickListener { changeIconsColor(2) }
        ivGrid!!.setOnClickListener { changeIconsColor(3) }

    }

    override fun onResume() {
        super.onResume()
        try {
            (activity as AppCompatActivity).supportActionBar!!.title = "Фільтри"
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }


    private fun changeIconsColor(id: Int) {

        this.currentSelect = id

        when (id) {
            1 -> {
                ivList!!.setImageDrawable(resources.getDrawable(R.drawable.microsoft_pink))
                ivMicrosoft!!.setImageDrawable(resources.getDrawable(R.drawable.view_list))
                ivGrid!!.setImageDrawable(resources.getDrawable(R.drawable.ic_grid_grey))
            }

            2 -> {
                ivList!!.setImageDrawable(resources.getDrawable(R.drawable.microsoft))
                ivMicrosoft!!.setImageDrawable(resources.getDrawable(R.drawable.viewlist_pink))
                ivGrid!!.setImageDrawable(resources.getDrawable(R.drawable.ic_grid_grey))
            }

            3 -> {
                ivList!!.setImageDrawable(resources.getDrawable(R.drawable.microsoft))
                ivMicrosoft!!.setImageDrawable(resources.getDrawable(R.drawable.view_list))
                ivGrid!!.setImageDrawable(resources.getDrawable(R.drawable.ic_grid_pink))
            }
        }
    }

}// Required empty public constructor
