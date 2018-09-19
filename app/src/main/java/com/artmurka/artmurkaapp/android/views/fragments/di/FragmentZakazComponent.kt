package com.artmurka.artmurkaapp.android.views.fragments.di

import com.artmurka.artmurkaapp.android.di.scopes.PerFragment
import com.artmurka.artmurkaapp.android.views.fragments.FragmenZakaz
import dagger.Subcomponent
import dagger.android.AndroidInjector


@PerFragment
@Subcomponent(modules = [ ])
interface FragmentZakazComponent : AndroidInjector<FragmenZakaz> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<FragmenZakaz>()

}