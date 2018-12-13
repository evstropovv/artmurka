package com.artmurka.artmurkaapp.android.views.activities.di

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.artmurka.artmurkaapp.android.di.scopes.PerActivity
import com.artmurka.artmurkaapp.android.views.activities.main.MainActivity
import com.artmurka.artmurkaapp.android.views.dialogs.ExitAcDialog
import com.artmurka.artmurkaapp.android.views.fragments.*
import com.artmurka.artmurkaapp.android.views.fragments.di.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import javax.inject.Singleton


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
        (BasketFragmentComponent::class),
        (CategoryFragmentComponent::class),
        (CategorySettingsComponent::class),
        (DeliveryFragmentComponent::class),
//        (FragmentAboutGoodsComponent::class),
        (FragmentCategoryChildsComponent::class),
        //       (FragmentDescriptionGoodsComponent::class),
        (FragmentZakazComponent::class),
        (IndividualFragmentComponent::class),
        (ItemListFragmentComponent::class),
        (OrderFragmentComponent::class),
        (WishFragmentComponent::class)
    ])
    interface FragmentBindingsModule {

        @Binds
        @IntoMap
        @FragmentKey(value = BasketFragment::class)
        fun basketFragmentComponentBuilder(builder: BasketFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>


        @Binds
        @IntoMap
        @FragmentKey(value = CategoryFragment::class)
        fun categoryFragmentComponentBuilder(builder: CategoryFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>


        @Binds
        @IntoMap
        @FragmentKey(value = CategorySettings::class)
        fun categorySettingsFragmentComponentBuilder(builder: CategorySettingsComponent.Builder): AndroidInjector.Factory<out Fragment>


        @Binds
        @IntoMap
        @FragmentKey(value = DeliveryFragment::class)
        fun deliveryFragmentComponent(builder: DeliveryFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>


        @Binds
        @IntoMap
        @FragmentKey(value = FragmentCategoryChilds::class)
        fun fragmentCategoryChildsGoodstComponent(builder: FragmentCategoryChildsComponent.Builder): AndroidInjector.Factory<out Fragment>


        @Binds
        @IntoMap
        @FragmentKey(value = FragmenZakaz::class)
        fun fragmentZakazComponentComponent(builder: FragmentZakazComponent.Builder): AndroidInjector.Factory<out Fragment>


        @Binds
        @IntoMap
        @FragmentKey(value = IndividualFragment::class)
        fun fragmentindividualFragmentComponent(builder: IndividualFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>


        @Binds
        @IntoMap
        @FragmentKey(value = ItemListFragment::class)
        fun itemListFragmentComponent(builder: ItemListFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>


        @Binds
        @IntoMap
        @FragmentKey(value = OrderFragment::class)
        fun orderFragmentComponentBuilder(builder: OrderFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>


        @Binds
        @IntoMap
        @FragmentKey(value = WishFragment::class)
        fun wishFragmentComponentBuilder(builder: WishFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    }

    @Module
    class ProvideModule {

        @Provides
        @PerActivity
        fun provideDialogExit(activity: AppCompatActivity): ExitAcDialog {
            return ExitAcDialog(activity)
        }

    }

    @Module
    interface BindingsModule {

    }

}