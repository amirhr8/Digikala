package ir.truelearn.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.truelearn.digikala.data.model.basket.CartItem
import ir.truelearn.digikala.data.model.category.SubCategory
import ir.truelearn.digikala.data.model.home.AmazingItem
import ir.truelearn.digikala.data.model.home.MainCategory
import ir.truelearn.digikala.data.model.home.Slider
import ir.truelearn.digikala.data.model.home.StoreProduct
import ir.truelearn.digikala.data.remote.NetworkResult
import ir.truelearn.digikala.repository.BasketRepository
import ir.truelearn.digikala.repository.CategoryRepository
import ir.truelearn.digikala.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(private val repository: BasketRepository) : ViewModel() {

    val suggestedList = MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())

    val currentCartItems: Flow<List<CartItem>> = repository.currentCartItems


    fun getSuggestedItems() {
        viewModelScope.launch {
            suggestedList.emit(repository.getSuggestedItems())
        }
    }


    fun insertCartItem(cart: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCartItem(cart)
        }
    }
}