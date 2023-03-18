package ir.truelearn.digikala.repository

import ir.truelearn.digikala.data.model.home.StoreProduct
import ir.truelearn.digikala.data.remote.BaseApiResponse
import ir.truelearn.digikala.data.remote.BasketApiInterface
import ir.truelearn.digikala.data.remote.NetworkResult
import javax.inject.Inject


class BasketRepository @Inject constructor(private val api: BasketApiInterface) : BaseApiResponse() {

    suspend fun getSuggestedItems(): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getSuggestedItems()
        }

}