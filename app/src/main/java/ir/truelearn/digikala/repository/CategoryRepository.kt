package ir.truelearn.digikala.repository

import ir.truelearn.digikala.data.model.category.SubCategory
import ir.truelearn.digikala.data.model.home.AmazingItem
import ir.truelearn.digikala.data.model.home.MainCategory
import ir.truelearn.digikala.data.model.home.Slider
import ir.truelearn.digikala.data.model.home.StoreProduct
import ir.truelearn.digikala.data.remote.BaseApiResponse
import ir.truelearn.digikala.data.remote.CategoryApiInterface
import ir.truelearn.digikala.data.remote.HomeApiInterface
import ir.truelearn.digikala.data.remote.NetworkResult
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val api: CategoryApiInterface) : BaseApiResponse() {

    suspend fun getSubCategories(): NetworkResult<SubCategory> =
        safeApiCall {
            api.getSubCategories()
        }


    suspend fun getProductByCategory(
        categoryName: String,
        pageSize: String,
        pageNumber: String,
    ): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getProductByCategory(categoryName, pageSize, pageNumber)
        }


    suspend fun getProductBySubCategory(
        subCategoryId: String,
        pageSize: String,
        pageNumber: String,
    ): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getProductBySubCategory(subCategoryId, pageSize, pageNumber)
        }

}