package com.artmurka.artmurkaapp.android.views.activities.di

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.artmurka.artmurkaapp.android.di.scopes.PerActivity
import com.artmurka.artmurkaapp.android.views.activities.selectedgood.SelectedGoodActivity
import com.artmurka.artmurkaapp.android.views.fragments.*
import com.artmurka.artmurkaapp.android.views.fragments.di.*
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@PerActivity
@Subcomponent(modules = [
    (SelectedGoodActivityComponent.ActivityModule::class),
    (SelectedGoodActivityComponent.FragmentBindingsModule::class),
    (SelectedGoodActivityComponent.BindingsModule::class),
    (SelectedGoodActivityComponent.ProvideModule::class)
])
interface SelectedGoodActivityComponent : AndroidInjector<SelectedGoodActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SelectedGoodActivity>()

    @Module
    interface ActivityModule {

        @Binds
        fun provideCheckoutActivityModule(activity: SelectedGoodActivity): AppCompatActivity

    }

    @Module(subcomponents = [
        (FragmentAboutGoodsComponent::class),
        (FragmentDescriptionGoodsComponent::class)
    ])
    interface FragmentBindingsModule {


        @Binds
        @IntoMap
        @FragmentKey(value = FragmentAboutGoods::class)
        fun fragmentAboutGoodstComponent(builder: FragmentAboutGoodsComponent.Builder): AndroidInjector.Factory<out Fragment>


        @Binds
        @IntoMap
        @FragmentKey(value = FragmentDescriptionGoods::class)
        fun fFragmentDescriptionGoodsComponent(builder: FragmentDescriptionGoodsComponent.Builder): AndroidInjector.Factory<out Fragment>

    }

    @Module
    class ProvideModule {}

    @Module
    interface BindingsModule {}
}
