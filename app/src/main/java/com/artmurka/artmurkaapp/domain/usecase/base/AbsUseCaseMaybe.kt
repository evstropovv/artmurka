package com.artmurka.artmurkaapp.domain.usecase.base
import io.reactivex.Maybe
import io.reactivex.MaybeTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers

abstract class AbsUseCaseMaybe<Result, Params> : AbsBaseUseCase() {


    abstract fun buildUseCaseObservable(@NonNull params: Params): Maybe<Result>

    fun execute(@NonNull observer: DisposableMaybeObserver<Result>, @Nullable params: Params) {
        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        addDisposable(observable.subscribeWith(observer))
    }

    fun execute(@NonNull observer: DisposableMaybeObserver<Result>, @NonNull transformer: MaybeTransformer<Result, Result>, @Nullable params: Params) {
        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(transformer)

        addDisposable(observable.subscribeWith(observer))
    }
}
