package com.artmurka.artmurkaapp.android.views.fragments

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artmurka.artmurkaapp.presenter.Presenter
import com.artmurka.artmurkaapp.presenter.PresenterView
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable


abstract class BaseFragment : Fragment() {
    var rxPermissions: RxPermissions? = null
    var compositeDisposable: CompositeDisposable? = null


    override fun onAttach(context: Context?) {
        rxPermissions = RxPermissions(this)
        compositeDisposable = CompositeDisposable()
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onStart() {
        super.onStart()

        getFragmentPresenter().viewIsShown()
    }

    override fun onStop() {
        getFragmentPresenter().viewIsHidden()

        super.onStop()
    }

    override fun onDestroyView() {
        getFragmentPresenter().dropView()

        super.onDestroyView()
    }

    override fun onDestroy() {
        getFragmentPresenter().destroy()
        compositeDisposable?.dispose()

        super.onDestroy()
    }

    // HasSupportFragmentInjector

    @LayoutRes
    protected abstract fun getLayout(): Int

    protected abstract fun getFragmentPresenter(): Presenter<out PresenterView>
}
