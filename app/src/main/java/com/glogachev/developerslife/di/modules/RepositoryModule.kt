package com.glogachev.developerslife.di.modules

import com.glogachev.developerslife.data.RepositoryImpl
import com.glogachev.developerslife.domain.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideRepository(repoImpl: RepositoryImpl): Repository
}