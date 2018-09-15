package com.artmurka.artmurkaapp.android.views.activities.checkout.di

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.artmurka.artmurkaapp.android.di.scopes.PerActivity
import com.artmurka.artmurkaapp.android.views.activities.main.MainActivity
import com.artmurka.artmurkaapp.android.views.fragments.BasketFragment
import com.artmurka.artmurkaapp.android.views.fragments.CategoryFragment
import com.artmurka.artmurkaapp.android.views.fragments.DeliveryFragment
import com.artmurka.artmurkaapp.android.views.fragments.WishFragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap


@PerActivity
@Subcomponent(modules = [
    (MainActivityComponent.ActivityModule::class),
    (MainActivityComponent.FragmentBindingsModule::class),
    (MainActivityComponent.BindingsModule::class),
    (MainActivityComponent.ProvideModule::class)
])
interface MainActivityComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()

    @Module
    interface ActivityModule {

        @Binds
        fun provideActivityModule(activity: MainActivity): AppCompatActivity

    }

    @Module(subcomponents = [
//        (CateoryFragmentComponent::class),
//        (BasketFragmentComponent::class)
    ])
    interface FragmentBindingsModule {
//
//        @Binds
//        @IntoMap
//        @FragmentKey(value = CategoryFragment::class)
//        fun categoryFragmentComponentBuilder(builder: CategoryFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
//
//        @Binds
//        @IntoMap
//        @FragmentKey(value = BasketFragment::class)
//        fun basketFragmentComponentBuilder(builder: BasketFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
//
//        @Binds
//        @IntoMap
//        @FragmentKey(value = BasketFragment::class)
//        fun favoritesFragmentComponentBuilder(builder: FavoritesFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
//
//        @Binds
//        @IntoMap
//        @FragmentKey(value = DeliveryFragment::class)
//        fun deliveryFragmentComponent(builder:DeliveryFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
//
//        @Binds
//        @IntoMap
//        @FragmentKey(value = WishFragment::class)
//        fun wishFragmentComponentBuilder(builder: WishFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
//
//        @Binds
//        @IntoMap
//        @FragmentKey(value = WishFragment::class)
//        fun orderFragmentComponentBuilder(builder: OrderFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
//

    }

    @Module
    class ProvideModule {
    }

    @Module
    interface BindingsModule {

    }

}