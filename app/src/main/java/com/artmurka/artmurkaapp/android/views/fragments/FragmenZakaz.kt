package com.artmurka.artmurkaapp.android.views.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.CardView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.OrderDesc
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.Areas.AreasResponse
import com.artmurka.artmurkaapp.presenter.CheckoutPresenter
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.android.views.activities.main.MainActivity
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ICheckoutFragment
import com.artmurka.artmurkaapp.presenter.Presenter
import com.artmurka.artmurkaapp.presenter.PresenterView
import com.google.gson.Gson
import com.jakewharton.rxbinding2.widget.RxTextView

import java.util.ArrayList

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.function.BiFunction
import javax.inject.Inject


class FragmenZakaz : BaseFragment(), ICheckoutFragment {

    @Inject
    lateinit var presenter: CheckoutPresenter

    override fun getLayout(): Int = R.layout.fragment_zakaz

    override fun getFragmentPresenter(): Presenter<out PresenterView> = presenter

    lateinit var linearNovaPoshta: LinearLayout
    lateinit var linerPikup: LinearLayout
    lateinit var linearPayReciever: LinearLayout
    lateinit var linerLiqPay: LinearLayout
    private var tvChoseAdress: TextView? = null
    private var tvPikup: TextView? = null
    private var autoCompleteWarehouse: AutoCompleteTextView? = null
    private var autoCompleteCities: AutoCompleteTextView? = null
    private var linearPib: LinearLayout? = null
    private var linearPay: LinearLayout? = null
    private var npCheck = false
    private var liqPayCheck = false
    private var wasPayChoised = false
    private var cardCity: CardView? = null
    private var btnPostCheckout: Button? = null
    private var etPhone: EditText? = null
    private var etName: EditText? = null
    private var etLastName: EditText? = null
    private var etPatr: EditText? = null
    private var obsCity: Observable<CharSequence>? = null
    private var obsWarehouse: Observable<CharSequence>? = null
    private var obsName: Observable<CharSequence>? = null
    private var obsLastName: Observable<CharSequence>? = null
    private var obsName2: Observable<CharSequence>? = null
    private var obsPhone: Observable<CharSequence>? = null
    private val checkoutButtonDisposable: Disposable? = null

    @SuppressLint("CheckResult")
    private fun loadRxBindings() {
        obsCity = RxTextView.textChanges(autoCompleteCities!!)
        obsWarehouse = RxTextView.textChanges(autoCompleteWarehouse!!)
        obsName = RxTextView.textChanges(etName!!)
        obsLastName = RxTextView.textChanges(etLastName!!)
        obsName2 = RxTextView.textChanges(etPatr!!)
        obsPhone = RxTextView.textChanges(etPhone!!)
//TODO !!!
//        Observable.combineLatest(obsName!!, obsLastName!!, obsName2!!, obsPhone!!, obsCity!!, obsWarehouse!!) BiFunction{ name , lastname, obsName2, obsPhone, obsCity, obsWarehouse -> name
//        }
//
//        { name , lastname, obsName2, obsPhone, obsCity, obsWarehouse ->
//            (name.length > 1
//                    && lastname.length > 1
//                    && obsName2.length > 1
//                    && obsPhone.length > 1
//
//                    //if chosen delivery NP -> check city and warehouse length
//                    && (!npCheck || obsCity.length > 1 && obsWarehouse.length > 0)
//                    && wasPayChoised)
//        }
//                .subscribe { aBoolean -> btnPostCheckout!!.isEnabled = aBoolean!! }


//        Observable.combineLatest<CharSequence, CharSequence, CharSequence, CharSequence, CharSequence, CharSequence, Boolean>(obsName!!, obsLastName!!, obsName2!!, obsPhone!!, obsCity!!, obsWarehouse!!)
//
//        { name , lastname, obsName2, obsPhone, obsCity, obsWarehouse ->
//            (name.length > 1
//                    && lastname.length > 1
//                    && obsName2.length > 1
//                    && obsPhone.length > 1
//
//                    //if chosen delivery NP -> check city and warehouse length
//                    && (!npCheck || obsCity.length > 1 && obsWarehouse.length > 0)
//                    && wasPayChoised)
//        }
//                .subscribe { aBoolean -> btnPostCheckout!!.isEnabled = aBoolean!! }
    }

    override fun onStop() {
        super.onStop()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_zakaz, container, false)
        // Inflate the layout for this fragment
        initUI(view)




        autoCompleteWarehouse = view.findViewById<View>(R.id.autocompleteWarehouse) as AutoCompleteTextView

        btnPostCheckout!!.setOnClickListener {
            presenter.postCheckout(etPhone!!.text.toString(),
                    etName!!.text.toString() + " " + etLastName!!.text.toString()
                            + autoCompleteCities!!.text.toString() + autoCompleteWarehouse!!.text.toString(),
                    "va.evstropov@gmail.com", if (npCheck) "2" else "1", if (liqPayCheck) "2" else "1"
            )
        }

        return view
    }

    private fun initUI(view: View) {
        linearPib = view.findViewById<View>(R.id.linearPib) as LinearLayout
        linearPay = view.findViewById<View>(R.id.linearPay) as LinearLayout
        linearNovaPoshta = view.findViewById<View>(R.id.linearNovaPoshta) as LinearLayout
        linerPikup = view.findViewById<View>(R.id.linerPikup) as LinearLayout
        tvChoseAdress = view.findViewById<View>(R.id.tvChoseAdress) as TextView
        tvPikup = view.findViewById<View>(R.id.tvPikup) as TextView
        cardCity = view.findViewById<View>(R.id.cardCity) as CardView
        linearPayReciever = view.findViewById<View>(R.id.linearPayReciever) as LinearLayout
        linerLiqPay = view.findViewById<View>(R.id.linerLiqPay) as LinearLayout
        btnPostCheckout = view.findViewById<View>(R.id.btnZakaz) as Button
        etPhone = view.findViewById<View>(R.id.etPhone) as EditText
        etName = view.findViewById<View>(R.id.etName) as EditText
        etLastName = view.findViewById<View>(R.id.etLastName) as EditText
        etPatr = view.findViewById<View>(R.id.etPatronymic) as EditText
        //   spinnerRegion = (SearchableSpinner) view.findViewById(R.id.spinnerRegion);
        autoCompleteCities = view.findViewById<View>(R.id.spinnerCity) as AutoCompleteTextView
        autoCompleteCities!!.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l -> presenter.selectCity(i) }
        autoCompleteCities!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                val newText = charSequence.toString()
                Log.d("Log.d", "" + newText.length)
                if (newText.length > 1) {
                    presenter.getCities(newText)
                }
                if (newText.length == 0) {
                    Log.d("Log.d", "text.length() == 0 " + newText.length)
                    activity!!.runOnUiThread { setSpinnerCityChecked(false) }
                }
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })


    }

    private fun setSpinnerCityChecked(isChecked: Boolean?) {

    }


    override fun onResume() {
        super.onResume()
        loadRxBindings()
        linearNovaPoshta.setOnClickListener {
            npCheck = false
            setCheckNP(npCheck)
        }
        linerPikup.setOnClickListener {
            npCheck = true
            setCheckNP(npCheck)
        }
        linerLiqPay.setOnClickListener {
            liqPayCheck = true
            setLiqPaqCheck(liqPayCheck)
            wasPayChoised = true
        }
        linearPayReciever.setOnClickListener {
            liqPayCheck = false
            setLiqPaqCheck(liqPayCheck)
            wasPayChoised = true
        }

    }


    internal fun setCheckNP(check: Boolean) {
        if (!check) {
            linearNovaPoshta!!.background = resources.getDrawable(R.drawable.zakaz_frame_style_check)
            linerPikup!!.background = resources.getDrawable(R.drawable.zakaz_frame_style)
            tvChoseAdress!!.visibility = View.VISIBLE
            tvPikup!!.visibility = View.GONE
            cardCity!!.visibility = View.VISIBLE

        } else {
            linearNovaPoshta!!.background = resources.getDrawable(R.drawable.zakaz_frame_style)
            linerPikup!!.background = resources.getDrawable(R.drawable.zakaz_frame_style_check)
            tvChoseAdress!!.visibility = View.GONE
            tvPikup!!.visibility = View.VISIBLE
            cardCity!!.visibility = View.GONE
        }
        linearPay!!.visibility = View.VISIBLE
    }

    fun setLiqPaqCheck(liqPaqCheck: Boolean) {
        if (!liqPaqCheck) {
            linearPayReciever.background = resources.getDrawable(R.drawable.zakaz_frame_style_check)
            linerLiqPay.background = resources.getDrawable(R.drawable.zakaz_frame_style)
        } else {
            linearPayReciever.background = resources.getDrawable(R.drawable.zakaz_frame_style)
            linerLiqPay.background = resources.getDrawable(R.drawable.zakaz_frame_style_check)
        }
        linearPib!!.visibility = View.VISIBLE
    }

    override fun showCheckout(list: List<OrderDesc>) {

    }

    override fun refreshSumPrice(price: String) {

    }

    override fun showOrderIsProcessed(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    override fun setDataSpinner(delivery: ArrayList<String>, payment: ArrayList<String>) {

    }


    override fun setSityes(sityes: Array<String>) {

    }

    override fun showMessage(message: String) {

    }

    override fun setAreas(areas: AreasResponse) {

    }


    override fun setCities(cityList: List<String>) {
        Log.d("Log.d", Gson().toJson(cityList))

        val adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_dropdown_item, cityList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        autoCompleteCities!!.setAdapter(adapter)

        adapter.notifyDataSetChanged()
    }

    override fun setWarehouses(warehouses: List<String>) {
        Log.d("Log.d", Gson().toJson(warehouses))
        val adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_dropdown_item, warehouses)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        autoCompleteWarehouse!!.setAdapter(adapter)
        adapter.notifyDataSetChanged()
    }

    override fun showDialog(msg: String) {
        val builder = AlertDialog.Builder(activity!!)
        builder.setMessage(msg)
                .setPositiveButton("На головну") { dialog, id -> startMainActivity(null) }
        builder.create().show()
    }


    private fun startMainActivity(msg: String?) {
        activity!!.finish()
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }
}// Required empty public constructor

