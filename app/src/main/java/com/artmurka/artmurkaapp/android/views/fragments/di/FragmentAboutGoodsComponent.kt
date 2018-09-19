package com.artmurka.artmurkaapp.android.views.fragments.di

import com.artmurka.artmurkaapp.android.di.scopes.PerFragment
import com.artmurka.artmurkaapp.android.views.fragments.FragmentAboutGoods
import dagger.Subcomponent
import dagger.android.AndroidInjector


@PerFragment
@Subcomponent(modules = [ ])
interface FragmentAboutGoodsComponent: AndroidInjector<FragmentAboutGoods> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<FragmentAboutGoods>()

}