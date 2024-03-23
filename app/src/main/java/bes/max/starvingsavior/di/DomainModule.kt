package bes.max.starvingsavior.di

import bes.max.starvingsavior.core.data.network.NetworkClient
import bes.max.starvingsavior.restaurants.data.repositories.RestaurantRepositoryImpl
import bes.max.starvingsavior.restaurants.domain.repositories.RestaurantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}