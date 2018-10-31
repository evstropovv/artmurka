package com.artmurka.artmurkaapp.presenter

import android.util.Log

import com.artmurka.artmurkaapp.BuildConfig
import com.artmurka.artmurkaapp.data.model.modules.ApiModuleNovaPoshta
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.DeliveryDescription
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.OrderDesc

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.PaymentDescription
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.CityResponse.Datum
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.CityRequest.City
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.CityRequest.MethodProperties
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.CityResponse.Address
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.ICheckoutPresenter
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ICheckoutFragment
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.WarehousesRequest.WarehouseRequest
import com.artmurka.artmurkaapp.domain.usecase.checkout.GetCheckoutUseCase
import com.artmurka.artmurkaapp.domain.usecase.checkout.PostCheckoutUseCase
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout.Success
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkoutresponse.CheckoutResponse
import java.util.ArrayList
import java.util.HashMap
import java.util.regex.Pattern
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CheckoutPresenter @Inject constructor(val getCheckoutUseCase: GetCheckoutUseCase,
                                            val postCheckoutUseCase: PostCheckoutUseCase) : BasePresenter<ICheckoutFragment>(), ICheckoutPresenter {
    private val isTextChanged = false
    private var deliveryList: ArrayList<String>? = null
    private var paymentList: ArrayList<String>? = null
    private var deliveryMap: HashMap<String, DeliveryDescription>? = null
    private var payMap: HashMap<String, PaymentDescription>? = null
    private var cities: List<Address>? = null
    private var warehouseList: List<com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.WarehousesResponse.Datum>? = null
    private var isViewDetached: Boolean? = false

    internal var disposables: MutableList<Disposable> = ArrayList()


    private val datumList: List<Datum>? = null


    override fun getData() {
        getCheckoutUseCase.execute(object : DisposableObserver<Success>() {
            override fun onComplete() {}
            override fun onNext(success: Success) {
                view?.showCheckout(getList(success.orderContent?.orderGoods!!))
                view?.refreshSumPrice(success.orderData?.orderAmount?.amountRaw!!.toString())

                //отображаем значения для выбора оплаты и выбора доставки
                if (deliveryList == null) {
                    deliveryList = ArrayList()
                    paymentList = ArrayList()

                    deliveryMap = success.deliveryList
                    payMap = success.paymentList

                    for (i in 0 until deliveryMap!!.size + 1) {
                        if (deliveryMap!![i.toString()] != null) {
                            deliveryList?.add(deliveryMap!![i.toString()]!!.name!!)
                        }
                    }

                    for (i in 0 until payMap!!.size + 1) {
                        if (payMap!![i.toString()] != null) {
                            paymentList!!.add(payMap!![i.toString()]!!.name!!)
                        }
                    }
                    view?.setDataSpinner(deliveryList!!, paymentList!!)
                }
            }

            override fun onError(e: Throwable) {}
        }, GetCheckoutUseCase.Params())
    }


    override fun postCheckout(telephone: String, message: String, email: String, pay: String, delivery: String) {
        Log.d("Log.d", "postCheckout")
        postCheckoutUseCase.execute(object : DisposableSingleObserver<CheckoutResponse>() {
            override fun onSuccess(response: CheckoutResponse) {
                if (response.error != null) {
                    view?.showOrderIsProcessed(response?.error?.msg!!)
                }

                if (response.success != null) {
                    view?.showDialog(response.success?.msg!!)
                }
            }

            override fun onError(e: Throwable) {}

        }, PostCheckoutUseCase.Params(telephone, message, email, pay, delivery))

    }

    override fun cityChanged(msg: String) {}

    override fun isEmailValid(email: String): Boolean? {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    override fun isValidPhone(phone: String): Boolean {
        val expression = "^([0-9\\+]|\\(\\d{1,3}\\))[0-9\\-\\. ]{8,15}$"
        val pattern = Pattern.compile(expression)
        val matcher = pattern.matcher(phone)
        return matcher.matches()
    }

    override fun selectCity(cityPosition: Int?) {
        val cityRef = cities!![cityPosition!!].deliveryCity
        Log.d("Log.d", "cityRef$cityRef")

        val warehouseRequest = WarehouseRequest()
        warehouseRequest.apiKey = BuildConfig.NP_API_KEY
        warehouseRequest.calledMethod = "getWarehouses" //getSettlements
        warehouseRequest.modelName = "AddressGeneral"
        warehouseRequest.methodProperties = com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.WarehousesRequest.MethodProperties(cityRef)
//        val disposable = ApiModuleNovaPoshta.getClient().getWarehouses(warehouseRequest)
//                .map<List<Datum>> { warehouseResponse -> warehouseResponse.data }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ data ->
//                    this.warehouseList = data
//                    val warehouses = ArrayList<String>()
//                    for (i in data.indices) {
//                        warehouses.add(data[i].getNumber() + " - " + data[i].description)
//
//                    }
//                    Log.d("Log.d", "setWarehouses " + Gson().toJson(warehouses))
//                    view?.setWarehouses(warehouses)
//
//                }, { error -> })
        //   disposables.add(disposable)
    }

    override fun getAreas() {}

    override fun getCities(cityName: String) {
        val city = City()
        city.apiKey = BuildConfig.NP_API_KEY
        city.calledMethod = "searchSettlements"
        // Log.d("Log.d", "select position "+datumList.get(pos).getAreasCenter());
        city.methodProperties = MethodProperties(cityName)
        city.modelName = "Address"

        val disposable = ApiModuleNovaPoshta.getClient().searhCity(city)
                .map { datumList -> datumList.data!![0].addresses!! }
                .doOnNext { addresses -> Log.d("Log.d", "list address size " + addresses.size + "") }
                //                .flatMap(addresses -> Flowable.fromIterable(addresses))
                //                .filter(addres -> {
                //                    return addres.getWarehouses() > 0;
                //                })
                //                .toSortedList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ addresses ->
                    cities = addresses
                    Log.d("Log.d", "list address size subscribe**" + addresses.size + "")

                    if (cities!!.size > 0) {
                        val cityStringList = ArrayList<String>()
                        for (i in addresses.indices) {
                            //отображаем города у которых 1+ склад новой почты
                            cityStringList.add(cities!![i].mainDescription!!)
                        }
                        view?.setCities(cityStringList)
                    }
                }, { throwable -> })
        disposables.add(disposable)
    }

    override fun selectWarehouse(warehousePosition: Int?) {

    }

    override fun detachView() {
        isViewDetached = true
        for (i in disposables.indices) {
            if (disposables[i] != null && !disposables[i].isDisposed) {
                disposables[i].dispose()
            }
        }
    }


    private fun getList(map: HashMap<String, OrderDesc>): List<OrderDesc> {
        val answerList = ArrayList<OrderDesc>()
        for (key in map.keys) {
            val desc = map[key]
            desc?.orderPosition = key
            answerList.add(desc!!)
        }
        return answerList
    }

    override fun onDropView() {
        getCheckoutUseCase.dispose()
        postCheckoutUseCase.dispose()
        super.onDropView()
    }
}
