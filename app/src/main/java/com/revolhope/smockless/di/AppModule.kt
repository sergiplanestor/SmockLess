package com.revolhope.smockless.di

import android.content.Context
import android.content.SharedPreferences
import com.revolhope.smockless.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesContext(@ApplicationContext appContext: Context): Context =
        appContext

    @Provides
    fun providesSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences =
        appContext.getSharedPreferences(
            appContext.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
}