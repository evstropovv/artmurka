package com.artmurka.artmurkaapp.android

import android.app.Activity
import android.app.Application
import android.app.Service
import android.support.v4.app.Fragment
import com.artmurka.artmurkaapp.android.di.app.DaggerApplicationComponent
import com.artmurka.artmurkaapp.android.di.module.ApplicationModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class ArtmurkaApplication : Application() , HasActivityInjector, HasSupportFragmentInjector, HasServiceInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var serviceInjector: DispatchingAndroidInjector<Service>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .create(this)
                .inject(this)
    }

    override fun activityInjector() = androidInjector
    override fun serviceInjector() = serviceInjector
    override fun supportFragmentInjector() = fragmentInjector

}