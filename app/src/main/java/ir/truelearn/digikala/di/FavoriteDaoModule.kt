package ir.truelearn.digikala.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.truelearn.digikala.data.db.CartDao
import ir.truelearn.digikala.data.db.DigikalaDatabase
import ir.truelearn.digikala.data.db.FavoriteListDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteDaoModule {

    @Provides
    @Singleton
    fun provideFavoriteListDao(
        database: DigikalaDatabase
    ): FavoriteListDao = database.FavoriteListDao()
}