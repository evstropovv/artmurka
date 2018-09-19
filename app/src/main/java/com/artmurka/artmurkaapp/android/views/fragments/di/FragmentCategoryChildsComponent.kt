package com.artmurka.artmurkaapp.android.views.fragments.di

import com.artmurka.artmurkaapp.android.di.scopes.PerFragment
import com.artmurka.artmurkaapp.android.views.fragments.FragmentCategoryChilds
import dagger.Subcomponent
import dagger.android.AndroidInjector


@PerFragment
@Subcomponent(modules = [ ])
interface FragmentCategoryChildsComponent : AndroidInjector<FragmentCategoryChilds> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<FragmentCategoryChilds>()

}