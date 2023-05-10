package ir.truelearn.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.truelearn.digikala.data.model.address.UserAddress
import ir.truelearn.digikala.data.remote.NetworkResult
import ir.truelearn.digikala.repository.AddressRepository
import ir.truelearn.digikala.util.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(private val repository: AddressRepository) :
    ViewModel() {

    val userAddressList =
        MutableStateFlow<NetworkResult<List<UserAddress>>>(NetworkResult.Loading())


    init {
        getUserAddressList(Constants.USER_TOKEN)
    }

    private fun getUserAddressList(token: String) {
        viewModelScope.launch {
            userAddressList.emit(repository.getUserAddressList(token))
        }
    }

}