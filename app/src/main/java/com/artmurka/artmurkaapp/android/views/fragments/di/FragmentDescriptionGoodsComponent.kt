package com.artmurka.artmurkaapp.android.views.fragments.di

import com.artmurka.artmurkaapp.android.di.scopes.PerFragment
import com.artmurka.artmurkaapp.android.views.fragments.FragmentDescriptionGoods
import dagger.Subcomponent
import dagger.android.AndroidInjector


@PerFragment
@Subcomponent(modules = [ ])
interface FragmentDescriptionGoodsComponent: AndroidInjector<FragmentDescriptionGoods> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<FragmentDescriptionGoods>()


}