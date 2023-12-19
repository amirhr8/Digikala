package ir.truelearn.digikala.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.truelearn.digikala.data.datastore.DataStoreRepository
import ir.truelearn.digikala.data.datastore.DataStoreRepositoryImpl
import ir.truelearn.digikala.data.remote.AddressApiInterface
import ir.truelearn.digikala.data.remote.CommentApiInterface
import ir.truelearn.digikala.data.remote.HomeApiInterface
import ir.truelearn.digikala.data.remote.ProductDetailApiInterface
import ir.truelearn.digikala.data.remote.ProfileApiInterface
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CommentApiInterfaceModule {

    @Singleton
    @Provides
    fun provideCommentApiService(retrofit: Retrofit) : CommentApiInterface =
        retrofit.create(CommentApiInterface::class.java)

}