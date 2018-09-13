package com.artmurka.artmurkaapp.domain.usecase.base
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class SubscriberUseCase<Result, in Params> {

    var compositeSubscription: CompositeDisposable = CompositeDisposable()


    abstract fun buildSubscriptionUseCase(params: Params): Observable<Result>

    private fun getScheduledSubscription(params: Params, transformer: ObservableTransformer<Result, Result>?): Observable<Result> {
        val observable = buildSubscriptionUseCase(params)
        if (transformer != null) {
            return observable.compose(transformer)
        }
        return observable
    }

    fun subscribe(observer: DisposableObserver<Result>, params: Params) = subscribe(observer, ObservableTransformer { upstream ->
        upstream.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }, params)

    fun subscribe(observer: DisposableObserver<Result>, transformer: ObservableTransformer<Result, Result>?, params: Params) {
        if (compositeSubscription.size() > 0) return
        compositeSubscription.add(getScheduledSubscription(params, transformer).subscribeWith(observer))
    }

    open fun clear() {
        compositeSubscription.clear()
    }

    open fun dispose() {
        compositeSubscription.dispose()
    }
}