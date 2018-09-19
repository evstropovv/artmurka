package com.artmurka.artmurkaapp.android.views.fragments.di

import com.artmurka.artmurkaapp.android.di.scopes.PerFragment
import com.artmurka.artmurkaapp.android.views.fragments.WishFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@PerFragment
@Subcomponent(modules = [ ])
interface WishFragmentComponent : AndroidInjector<WishFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<WishFragment>()

}