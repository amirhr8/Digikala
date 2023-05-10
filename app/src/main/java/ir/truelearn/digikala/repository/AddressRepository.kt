package ir.truelearn.digikala.repository

import ir.truelearn.digikala.data.model.address.UserAddress
import ir.truelearn.digikala.data.model.home.AmazingItem
import ir.truelearn.digikala.data.model.home.MainCategory
import ir.truelearn.digikala.data.model.home.Slider
import ir.truelearn.digikala.data.model.home.StoreProduct
import ir.truelearn.digikala.data.model.prfile.LoginRequest
import ir.truelearn.digikala.data.model.prfile.LoginResponse
import ir.truelearn.digikala.data.remote.*
import javax.inject.Inject

class AddressRepository @Inject constructor(private val api: AddressApiInterface) : BaseApiResponse() {

    suspend fun getUserAddressList(token: String): NetworkResult<List<UserAddress>> =
        safeApiCall {
          api.getUserAddressList(token)
        }


}