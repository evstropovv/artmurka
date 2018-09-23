package com.artmurka.artmurkaapp.android.views.fragments.di

import com.artmurka.artmurkaapp.android.di.scopes.PerFragment
import com.artmurka.artmurkaapp.android.views.fragments.DeliveryFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@PerFragment
@Subcomponent(modules = [ ])
interface DeliveryFragmentComponent: AndroidInjector<DeliveryFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DeliveryFragment>()

}