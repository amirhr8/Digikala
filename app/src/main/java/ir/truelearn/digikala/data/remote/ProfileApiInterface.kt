package ir.truelearn.digikala.data.remote

import ir.truelearn.digikala.data.model.ResponseResult
import ir.truelearn.digikala.data.model.home.AmazingItem
import ir.truelearn.digikala.data.model.home.MainCategory
import ir.truelearn.digikala.data.model.home.Slider
import ir.truelearn.digikala.data.model.home.StoreProduct
import retrofit2.Response
import retrofit2.http.GET

interface ProfileApiInterface {

    @GET("v1/getSlider")
    suspend fun getSlider() : Response<ResponseResult<List<Slider>>>


}