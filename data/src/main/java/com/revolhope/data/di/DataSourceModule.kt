package com.revolhope.data.di

import com.revolhope.data.common.preferences.datasource.PreferencesDataSource
import com.revolhope.data.feature.user.datasource.UserDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindsUserDataSource(dataSource: PreferencesDataSource): UserDataSource
}