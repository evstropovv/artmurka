package com.artmurka.artmurkaapp.domain.usecase.base

import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class AbsBaseUseCase internal constructor() {

    private var mDisposables: CompositeDisposable? = null

    fun dispose() {
        if (mDisposables != null && !mDisposables!!.isDisposed) {
            mDisposables!!.dispose()
        }
    }

    fun clear() {
        if (mDisposables != null) {
            mDisposables!!.clear()
        }
    }

    internal fun addDisposable(@NonNull disposable: Disposable) {
        this.mDisposables = CompositeDisposable()
        mDisposables!!.add(disposable)
    }
}
