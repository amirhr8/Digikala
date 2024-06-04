package ir.truelearn.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.truelearn.digikala.data.model.category.SubCategory
import ir.truelearn.digikala.data.model.home.AmazingItem
import ir.truelearn.digikala.data.model.home.MainCategory
import ir.truelearn.digikala.data.model.home.Slider
import ir.truelearn.digikala.data.model.home.StoreProduct
import ir.truelearn.digikala.data.remote.NetworkResult
import ir.truelearn.digikala.data.source.ProductByCategoryDataSource
import ir.truelearn.digikala.repository.CategoryRepository
import ir.truelearn.digikala.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepository) :
    ViewModel() {

    val subCategory = MutableStateFlow<NetworkResult<SubCategory>>(NetworkResult.Loading())

    var productByCategoryList: Flow<PagingData<StoreProduct>> = flow { emit(PagingData.empty()) }


    suspend fun getAllDataFromServer() {
        viewModelScope.launch {

            //fire and forget
            launch {
                subCategory.emit(repository.getSubCategories())
            }
        }
    }

    fun getProductByCategory(categoryId: String){
        productByCategoryList = Pager(
            PagingConfig(pageSize = 5)
        ){
            ProductByCategoryDataSource(repository ,categoryId )
        }.flow.cachedIn(viewModelScope)
    }

}