package com.artmurka.artmurkaapp.android.views.activities.di

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.artmurka.artmurkaapp.android.di.scopes.PerActivity
import com.artmurka.artmurkaapp.android.views.activities.checkout.CheckoutActivity
import com.artmurka.artmurkaapp.android.views.fragments.FragmenZakaz
import com.artmurka.artmurkaapp.android.views.fragments.FragmentAboutGoods
import com.artmurka.artmurkaapp.android.views.fragments.FragmentDescriptionGoods
import com.artmurka.artmurkaapp.android.views.fragments.di.FragmentAboutGoodsComponent
import com.artmurka.artmurkaapp.android.views.fragments.di.FragmentDescriptionGoodsComponent
import com.artmurka.artmurkaapp.android.views.fragments.di.FragmentZakazComponent
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap



@PerActivity
@Subcomponent(modules = [
    (CheckoutActivityComponent.ActivityModule::class),
    (CheckoutActivityComponent.FragmentBindingsModule::class),
    (CheckoutActivityComponent.BindingsModule::class),
    (CheckoutActivityComponent.ProvideModule::class)
])
interface CheckoutActivityComponent : AndroidInjector<CheckoutActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<CheckoutActivity>()

    @Module
    interface ActivityModule {

        @Binds
        fun provideCheckoutActivityModule(activity: CheckoutActivity): AppCompatActivity

    }

    @Module(subcomponents = [
        (FragmentZakazComponent::class)
    ])
    interface FragmentBindingsModule {

        @Binds
        @IntoMap
        @FragmentKey(value = FragmenZakaz::class)
        fun fragmentZakazComponent(builder: FragmentZakazComponent.Builder): AndroidInjector.Factory<out Fragment>


    }

    @Module
    class ProvideModule {}

    @Module
    interface BindingsModule {}
}
