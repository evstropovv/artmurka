package com.artmurka.artmurkaapp.android.views.fragments.di

import com.artmurka.artmurkaapp.android.di.scopes.PerFragment
import com.artmurka.artmurkaapp.android.views.fragments.IndividualFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector


@PerFragment
@Subcomponent(modules = [ ])
interface IndividualFragmentComponent: AndroidInjector<IndividualFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<IndividualFragment>()

}