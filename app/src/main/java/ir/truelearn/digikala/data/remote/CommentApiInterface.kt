package ir.truelearn.digikala.data.remote

import ir.truelearn.digikala.data.model.ResponseResult
import ir.truelearn.digikala.data.model.checkout.OrderDetail
import ir.truelearn.digikala.data.model.home.StoreProduct
import ir.truelearn.digikala.data.model.product_detail.Comment
import ir.truelearn.digikala.data.model.product_detail.NewComment
import ir.truelearn.digikala.data.model.product_detail.ProductDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CommentApiInterface {

    @POST("v1/setNewComment")
    suspend fun setNewComment(
        @Body newComment: NewComment
    ): Response<ResponseResult<String>>

    @GET("v1/getAllProductComments")
    suspend fun getAllProductComments(
        @Query("id") id: String,
        @Query("pageSize") pageSize: String,
        @Query("pageNumber") pageNumber: String,
    ): Response<ResponseResult<List<Comment>>>

    @GET("v1/getUserComments")
    suspend fun getUserComments(
        @Query("token") token: String,
        @Query("pageSize") pageSize: String,
        @Query("pageNumber") pageNumber: String,
    ): Response<ResponseResult<List<Comment>>>

}