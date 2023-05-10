package ir.truelearn.digikala.data.remote

import ir.truelearn.digikala.data.model.ResponseResult
import ir.truelearn.digikala.data.model.address.UserAddress
import ir.truelearn.digikala.data.model.home.AmazingItem
import ir.truelearn.digikala.data.model.home.MainCategory
import ir.truelearn.digikala.data.model.home.Slider
import ir.truelearn.digikala.data.model.home.StoreProduct
import ir.truelearn.digikala.data.model.prfile.LoginRequest
import ir.truelearn.digikala.data.model.prfile.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AddressApiInterface {

    @GET("v1/getUserAddress")
    suspend fun getUserAddressList(
        @Query("token") token: String
    ) : Response<ResponseResult<List<UserAddress>>>


}