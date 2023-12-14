package ir.truelearn.digikala.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.truelearn.digikala.data.model.home.AmazingItem
import ir.truelearn.digikala.data.model.home.MainCategory
import ir.truelearn.digikala.data.model.home.Slider
import ir.truelearn.digikala.data.model.home.StoreProduct
import ir.truelearn.digikala.data.model.prfile.LoginRequest
import ir.truelearn.digikala.data.model.prfile.LoginResponse
import ir.truelearn.digikala.data.model.prfile.SetUserNameRequest
import ir.truelearn.digikala.data.remote.NetworkResult
import ir.truelearn.digikala.repository.HomeRepository
import ir.truelearn.digikala.repository.ProfileRepository
import ir.truelearn.digikala.ui.screens.profile.ProfileScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) :
    ViewModel() {
    //sharedViewModel
    var screenState by mutableStateOf(ProfileScreenState.LOGIN_STATE)

    var inputPhoneState by mutableStateOf("")
    var inputPasswordState by mutableStateOf("")
    var loadingState by mutableStateOf(false)

    val loginResponse = MutableStateFlow<NetworkResult<LoginResponse>>(NetworkResult.Loading())

    val setUserNameResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    fun login() {
        viewModelScope.launch {
            loadingState = true
            val loginRequest = LoginRequest(inputPhoneState, inputPasswordState)
            loginResponse.emit(repository.login(loginRequest))
        }
    }


    fun refreshToken(phone: String, password: String){
        viewModelScope.launch {
            val loginRequest = LoginRequest(phone , password)
            loginResponse.emit(repository.login(loginRequest))
        }
    }

    fun setUserName (newUserName: SetUserNameRequest){
        viewModelScope.launch {
            setUserNameResponse.emit(repository.setUserName(newUserName))
        }
    }

}