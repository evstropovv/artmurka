package com.artmurka.artmurkaapp.presenter

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.artmurka.artmurkaapp.R
import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Success
import com.artmurka.artmurkaapp.presenter.interfaces_presenter.ICategoryPresenter
import com.artmurka.artmurkaapp.android.views.fragments.interfaces.ICategoryFragment
import com.artmurka.artmurkaapp.domain.usecase.categories.GetCategoriesUseCase
import com.artmurka.artmurkaapp.other.Const
import java.util.ArrayList
import javax.inject.Inject
import io.reactivex.observers.DisposableObserver


class CategoryPresenter @Inject constructor(val model: GetCategoriesUseCase, val context: Context) : BasePresenter<ICategoryFragment>(), ICategoryPresenter {


    private var successList: ArrayList<Success>? = null

    fun makeCall() {
        val call = Uri.parse("tel:" + Const.TEL_NUMBER)
        val surf = Intent(Intent.ACTION_DIAL, call)
        surf.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(surf)
    }

    override fun getCategoriesData(isUpdate: Boolean) {
        if (successList == null) {
            resetData()
        } else {
            view?.showCategories(successList!!)
        }
    }

    private fun resetData() {
        model.execute(object : DisposableObserver<ArrayList<Success>>() {
            override fun onComplete() {}
            override fun onNext(t: ArrayList<Success>) {
                successList = t
                if (successList?.isEmpty()!!)
                    view?.showError("Error, succes list is Empty")
                else
                    view?.showCategories(successList!!)
            }

            override fun onError(e: Throwable) {
                view?.showError(context.resources.getString(R.string.any_error_reload))
            }
        }, GetCategoriesUseCase.Params())

    }

    override fun onDropView() {
        model.dispose()
        super.onDropView()
    }
}

