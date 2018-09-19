package com.artmurka.artmurkaapp.android.views.fragments.di

import com.artmurka.artmurkaapp.android.di.scopes.PerFragment
import com.artmurka.artmurkaapp.android.views.fragments.ItemListFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector


@PerFragment
@Subcomponent(modules = [ ])
interface ItemListFragmentComponent: AndroidInjector<ItemListFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ItemListFragment>()

}