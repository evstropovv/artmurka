package com.artmurka.artmurkaapp.android.views.fragments.di

import com.artmurka.artmurkaapp.android.di.scopes.PerFragment
import com.artmurka.artmurkaapp.android.views.fragments.OrderFragment
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector


@PerFragment
@Subcomponent(modules = [ ])
interface OrderFragmentComponent : AndroidInjector<OrderFragment>{

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<OrderFragment>()

}