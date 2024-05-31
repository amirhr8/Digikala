package ir.truelearn.digikala.repository

import ir.truelearn.digikala.data.model.checkout.OrderFullDetail
import ir.truelearn.digikala.data.model.home.AmazingItem
import ir.truelearn.digikala.data.model.home.MainCategory
import ir.truelearn.digikala.data.model.home.Slider
import ir.truelearn.digikala.data.model.home.StoreProduct
import ir.truelearn.digikala.data.model.prfile.LoginRequest
import ir.truelearn.digikala.data.model.prfile.LoginResponse
import ir.truelearn.digikala.data.model.prfile.SetUserNameRequest
import ir.truelearn.digikala.data.remote.BaseApiResponse
import ir.truelearn.digikala.data.remote.HomeApiInterface
import ir.truelearn.digikala.data.remote.NetworkResult
import ir.truelearn.digikala.data.remote.ProfileApiInterface
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val api: ProfileApiInterface) :
    BaseApiResponse() {

    suspend fun login(loginRequest: LoginRequest): NetworkResult<LoginResponse> =
        safeApiCall {
            api.login(loginRequest)
        }

    suspend fun setUserName(newUserName: SetUserNameRequest): NetworkResult<String> =
        safeApiCall {
            api.setUserName(newUserName)
        }


    suspend fun getUserOrders(token: String): NetworkResult<List<OrderFullDetail>>  =
        safeApiCall {
            api.getUserOrders(token)
        }

}