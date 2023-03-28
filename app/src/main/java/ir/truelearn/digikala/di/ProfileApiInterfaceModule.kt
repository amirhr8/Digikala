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
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ProfileApiInterfaceModule {

    @Singleton
    @Provides
    fun provideProfileApiService(retrofit: Retrofit) : ProfileApiInterface =
        retrofit.create(ProfileApiInterface::class.java)

}