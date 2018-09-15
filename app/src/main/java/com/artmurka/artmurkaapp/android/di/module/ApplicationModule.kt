package com.artmurka.artmurkaapp.android.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.artmurka.artmurkaapp.android.di.scopes.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
open class ApplicationModule(val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext


}