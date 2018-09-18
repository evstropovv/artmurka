package com.artmurka.artmurkaapp.presenter

import android.util.Log
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Example
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Success
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.ICategoryPresenter
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ICategoryFragment
import com.artmurka.artmurkaapp.data.model.modules.AllRequestOvservers
import com.google.gson.Gson
import java.util.ArrayList
import javax.inject.Inject
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


class CategoryPresenter @Inject constructor(val model: AllRequestOvservers) : BasePresenter<ICategoryFragment>(), ICategoryPresenter {


    private var successList: ArrayList<Success>? = null
    private var exampleObservable: Observable<Example>? = null

    override fun getCategoriesData(isUpdate: Boolean) {
        if (successList == null) {
            resetData()
        } else {
            view?.showCategories(successList!!)
        }
    }

    private fun resetData() {

        exampleObservable = model!!.categories
        exampleObservable!!.subscribe(object : Observer<Example> {
            override fun onSubscribe(d: Disposable) {}

            override fun onNext(value: Example) {
                successList = value.success
                if (successList!!.isEmpty())
                    view?.showError("Error, succes list is Empty")
                else
                    view?.showCategories(successList!!)

                Log.d("Log.d", Gson().toJson(value.success))
            }

            override fun onError(e: Throwable) {
                view?.showError("Щось пішло не так. Перезавантажити ?")

            }

            override fun onComplete() {}
        })
    }
}
