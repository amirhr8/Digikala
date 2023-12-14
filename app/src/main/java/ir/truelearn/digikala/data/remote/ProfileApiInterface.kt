package ir.truelearn.digikala.data.remote

import ir.truelearn.digikala.data.model.ResponseResult
import ir.truelearn.digikala.data.model.home.AmazingItem
import ir.truelearn.digikala.data.model.home.MainCategory
import ir.truelearn.digikala.data.model.home.Slider
import ir.truelearn.digikala.data.model.home.StoreProduct
import ir.truelearn.digikala.data.model.prfile.LoginRequest
import ir.truelearn.digikala.data.model.prfile.LoginResponse
import ir.truelearn.digikala.data.model.prfile.SetUserNameRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProfileApiInterface {

    @POST("v1/login")
    suspend fun login(
        @Body login : LoginRequest
    ) : Response<ResponseResult<LoginResponse>>


    @POST("v1/setUserName")
    suspend fun setUserName(
        @Body setUserName : SetUserNameRequest
    ) : Response<ResponseResult<String>>


}