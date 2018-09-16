package com.artmurka.artmurkaapp.android.di.app

import android.app.Activity
import com.artmurka.artmurkaapp.android.ArtmurkaApplication
import com.artmurka.artmurkaapp.android.di.module.ApplicationModule
import com.artmurka.artmurkaapp.android.views.activities.checkout.di.MainActivityComponent
import com.artmurka.artmurkaapp.android.views.activities.fullphoto.FullPhotoActivity
import com.artmurka.artmurkaapp.android.views.activities.main.MainActivity
import com.artmurka.artmurkaapp.data.model.di.DataModule
import com.artmurka.artmurkaapp.data.model.di.RequestModule
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.multibindings.IntoMap
import javax.inject.Singleton


@Singleton
@Component(modules = [
    (ApplicationModule::class),
    (RequestModule::class),
    (AndroidSupportInjectionModule::class),
    (ApplicationComponent.ActivityBindingsModule::class),
    (ApplicationComponent.FragmentBindingsModule::class),
    (ApplicationComponent.ServiceBindingsModule::class)
])
interface ApplicationComponent : AndroidInjector<ArtmurkaApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<ArtmurkaApplication>() {
        abstract fun applicationModule(module: ApplicationModule): Builder
    }

    @Module(subcomponents = [
        (MainActivityComponent::class)
    ])
    interface ActivityBindingsModule {

        @Binds
        @IntoMap
        @ActivityKey(value = MainActivity::class)
        fun mainActivityComponentBuilder(builder: MainActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    }
    @Module(subcomponents = [ ])

    interface FragmentBindingsModule {

    }

    @Module(subcomponents = [  ])
    interface ServiceBindingsModule {

    }
}