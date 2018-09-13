package com.artmurka.artmurkaapp.domain.usecase.base
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class AbsUseCaseObs<Result, Params> : AbsBaseUseCase() {


    abstract fun buildUseCaseObservable(@NonNull params: Params): Observable<Result>

    fun execute(@NonNull observer: DisposableObserver<Result>, @NonNull params: Params) {
        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        addDisposable(observable.subscribeWith (observer))
    }

    fun execute(@NonNull observer: DisposableObserver<Result>, @NonNull transformer: ObservableTransformer<Result, Result>, @Nullable params: Params) {
        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(transformer)
        addDisposable(observable.subscribeWith (observer))
    }
}
