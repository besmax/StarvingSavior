package bes.max.starvingsavior.di

import android.content.Context
import bes.max.starvingsavior.core.data.navigation.ExternalNavigatorImpl
import bes.max.starvingsavior.core.data.network.NetworkClient
import bes.max.starvingsavior.core.domain.navigation.ExternalNavigator
import bes.max.starvingsavior.restaurants.data.repositories.RestaurantRepositoryImpl
import bes.max.starvingsavior.restaurants.domain.repositories.RestaurantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideRestaurantRepository(networkClient: NetworkClient): RestaurantRepository {
        return RestaurantRepositoryImpl(networkClient)
    }

    @Provides
    @Singleton
    fun provideExternalNavigator(@ApplicationContext context: Context): ExternalNavigator {
        return ExternalNavigatorImpl(context)
    }
}