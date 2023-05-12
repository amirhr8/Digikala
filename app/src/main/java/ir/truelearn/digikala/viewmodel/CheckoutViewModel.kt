package ir.truelearn.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.truelearn.digikala.data.remote.NetworkResult
import ir.truelearn.digikala.repository.CheckoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val repository: CheckoutRepository) :
    ViewModel() {


    val shippingCost = MutableStateFlow<NetworkResult<Int>>(NetworkResult.Loading())

    fun getShippingCost(address : String){
        viewModelScope.launch {
            shippingCost.emit(repository.getShippingCost(address))
        }
    }


}