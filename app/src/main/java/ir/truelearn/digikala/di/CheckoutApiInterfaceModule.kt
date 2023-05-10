package ir.truelearn.digikala.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.truelearn.digikala.data.remote.CheckoutApiInterface
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CheckoutApiInterfaceModule {

    @Singleton
    @Provides
    fun provideCheckoutApiService(retrofit: Retrofit) : CheckoutApiInterface =
        retrofit.create(CheckoutApiInterface::class.java)

}