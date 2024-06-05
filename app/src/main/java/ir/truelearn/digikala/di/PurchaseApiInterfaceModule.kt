package ir.truelearn.digikala.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.truelearn.digikala.data.datastore.DataStoreRepository
import ir.truelearn.digikala.data.datastore.DataStoreRepositoryImpl
import ir.truelearn.digikala.data.remote.HomeApiInterface
import ir.truelearn.digikala.data.remote.ProfileApiInterface
import ir.truelearn.digikala.data.remote.PurchaseApiInterface
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PurchaseApiInterfaceModule {

    @Singleton
    @Provides
    fun providePurchaseApiService(@Named("purchaseUrl") retrofit: Retrofit) : PurchaseApiInterface =
        retrofit.create(PurchaseApiInterface::class.java)

}