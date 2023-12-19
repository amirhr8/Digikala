package ir.truelearn.digikala.repository

import ir.truelearn.digikala.data.model.home.StoreProduct
import ir.truelearn.digikala.data.model.product_detail.Comment
import ir.truelearn.digikala.data.model.product_detail.NewComment
import ir.truelearn.digikala.data.model.product_detail.ProductDetail
import ir.truelearn.digikala.data.remote.BaseApiResponse
import ir.truelearn.digikala.data.remote.CommentApiInterface
import ir.truelearn.digikala.data.remote.NetworkResult
import ir.truelearn.digikala.data.remote.ProductDetailApiInterface
import retrofit2.http.Query
import javax.inject.Inject

class CommentRepository @Inject constructor(private val api: CommentApiInterface) :
    BaseApiResponse() {

    suspend fun setNewComment(newComment: NewComment): NetworkResult<String> =
        safeApiCall {
            api.setNewComment(newComment)
        }


    suspend fun getAllProductComments(
        id: String,
        pageSize: String,
        pageNumber: String,
    ): NetworkResult<List<Comment>> =
        safeApiCall {
            api.getAllProductComments(id, pageSize, pageNumber)
        }


    suspend fun getUserComments(
        token: String,
        pageSize: String,
        pageNumber: String,
    ): NetworkResult<List<Comment>> =
        safeApiCall {
            api.getUserComments(token, pageSize, pageNumber)
        }


}