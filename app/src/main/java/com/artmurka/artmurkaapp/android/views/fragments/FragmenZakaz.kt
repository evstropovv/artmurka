package com.artmurka.artmurkaapp.android.views.fragments

import android.Manifest
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
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.tbruyelle.rxpermissions2.RxPermissions

import java.util.ArrayList

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_zakaz.*
import kotlinx.android.synthetic.main.zakaz.*
import java.util.function.BiFunction
import javax.inject.Inject


class FragmenZakaz : BaseFragment(), ICheckoutFragment {

    @Inject
    lateinit var presenter: CheckoutPresenter

    override fun getLayout(): Int = R.layout.fragment_zakaz

    override fun getFragmentPresenter(): Presenter<out PresenterView> = presenter
    private var npCheck = false
    private var liqPayCheck = false
    private var wasPayChoised = false

    private var obsCity: Observable<CharSequence>? = null
    private var obsWarehouse: Observable<CharSequence>? = null
    private var obsName: Observable<CharSequence>? = null
    private var obsLastName: Observable<CharSequence>? = null
    private var obsName2: Observable<CharSequence>? = null
    private var obsPhone: Observable<CharSequence>? = null

    @SuppressLint("CheckResult")
    private fun loadRxBindings() {
        obsCity = RxTextView.textChanges(spinnerCity)
        obsWarehouse = RxTextView.textChanges(autocompleteWarehouse)
        obsName = RxTextView.textChanges(etName)
        obsLastName = RxTextView.textChanges(etLastName)
        obsName2 = RxTextView.textChanges(etPatronymic)
        obsPhone = RxTextView.textChanges(etPhone)
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


    override fun onStart() {
        initUI()
        super.onStart()
    }

    private fun initUI() {
        btnZakaz.setOnClickListener {
            presenter.postCheckout(etPhone!!.text.toString(),
                    etName!!.text.toString() + " " + etLastName!!.text.toString()
                            + spinnerCity.text.toString() + spinnerCity.text.toString(),
                    "va.evstropov@gmail.com", if (npCheck) "2" else "1", if (liqPayCheck) "2" else "1"
            )
        }
        spinnerCity.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l -> presenter.selectCity(i) }
        spinnerCity.addTextChangedListener(object : TextWatcher {
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

        compositeDisposable?.add(RxView.clicks(linerLiqPay)
                .compose(rxPermissions?.ensure(Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.READ_PHONE_STATE))
                .subscribe { granted ->
                    if (granted) {
                        liqPayCheck = true
                        setLiqPaqCheck(liqPayCheck)
                        wasPayChoised = true
                    }
                })

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
        spinnerCity.setAdapter(adapter)
        adapter.notifyDataSetChanged()
    }

    override fun setWarehouses(warehouses: List<String>) {
        Log.d("Log.d", Gson().toJson(warehouses))
        val adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_dropdown_item, warehouses)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        autocompleteWarehouse.setAdapter(adapter)
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
}

