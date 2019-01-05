package com.artmurka.artmurkaapp

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

public class TestSchedulersRule : TestRule {

    override fun apply(base: Statement?, description: Description?): Statement {
        initTestSchedulers()
        return base!!
    }

    private fun initTestSchedulers() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { h -> Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { h -> Schedulers.trampoline() }
    }
}