package ir.truelearn.digikala.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.truelearn.digikala.data.model.address.UserAddress
import ir.truelearn.digikala.data.model.home.StoreProduct
import ir.truelearn.digikala.data.model.product_detail.Comment
import ir.truelearn.digikala.data.model.product_detail.NewComment
import ir.truelearn.digikala.data.model.product_detail.ProductDetail
import ir.truelearn.digikala.data.remote.NetworkResult
import ir.truelearn.digikala.data.source.ProductCommentsDataSource
import ir.truelearn.digikala.data.source.UserCommentsDataSource
import ir.truelearn.digikala.repository.AddressRepository
import ir.truelearn.digikala.repository.CommentRepository
import ir.truelearn.digikala.repository.ProductDetailRepository
import ir.truelearn.digikala.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(private val repository: CommentRepository) :
    ViewModel() {


    val newCommentResult =
        MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())


    var productCommentsList: Flow<PagingData<Comment>> = flow { emit(PagingData.empty()) }


    var UserCommentsList: Flow<PagingData<Comment>> = flow { emit(PagingData.empty()) }


    fun setNewComment(newComment: NewComment) {
        viewModelScope.launch {
            newCommentResult.emit(repository.setNewComment(newComment))
        }
    }


    fun getCommentList(productId: String) {
        productCommentsList = Pager(
            PagingConfig(pageSize = 5)
        ) {
            ProductCommentsDataSource(repository, productId)
        }.flow.cachedIn(viewModelScope)
    }


    fun getUserComments() {
        UserCommentsList = Pager(
            PagingConfig(pageSize = 5)
        ) {
            UserCommentsDataSource(repository, Constants.USER_TOKEN)
        }.flow.cachedIn(viewModelScope)
    }



}