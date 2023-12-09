package ir.truelearn.digikala.repository

import ir.truelearn.digikala.data.db.FavoriteListDao
import ir.truelearn.digikala.data.model.prfile.FavItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteListRepository @Inject constructor(
    private val favoriteListDao: FavoriteListDao
) {

    val allFavoriteItems: Flow<List<FavItem>> = favoriteListDao.getAllFavoriteItems()

    suspend fun removeFavoriteItem(favItem: FavItem) {
        favoriteListDao.removeFavoriteItem(favItem)
    }

    suspend fun addFavoriteItem(favItem: FavItem) {
        favoriteListDao.addFavoriteItem(favItem)
    }

    suspend fun clearFavoriteList() {
        favoriteListDao.clearFavoriteList()
    }

    fun isFavoriteItemExist(itemId: String): Flow<Boolean> =
        favoriteListDao.isFavoriteItemExist(itemId)

}