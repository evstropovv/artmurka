package com.artmurka.artmurkaapp.domain.usecase.base
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class AbsUseCaseSingle<Result, in Params> : AbsBaseUseCase() {

    abstract fun buildUseCaseObservable(@NonNull params: Params): Single<Result>

    fun execute(@NonNull observer: DisposableSingleObserver<Result>, @Nullable params: Params) {
        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        addDisposable(observable.subscribeWith(observer))
    }

    fun execute(@NonNull observer: DisposableSingleObserver<Result>, @NonNull transformer: SingleTransformer<Result, Result>, @Nullable params: Params) {
        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(transformer)

        addDisposable(observable.subscribeWith(observer))
    }
}
