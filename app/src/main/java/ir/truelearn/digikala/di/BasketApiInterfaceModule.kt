package ir.truelearn.digikala.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.truelearn.digikala.data.remote.BasketApiInterface
import ir.truelearn.digikala.data.remote.CategoryApiInterface
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object BasketApiInterfaceModule {

    @Singleton
    @Provides
    fun provideBasketApiService(retrofit: Retrofit): BasketApiInterface =
        retrofit.create(BasketApiInterface::class.java)

}