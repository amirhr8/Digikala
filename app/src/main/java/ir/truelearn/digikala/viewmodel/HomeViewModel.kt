package ir.truelearn.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.truelearn.digikala.data.model.home.Slider
import ir.truelearn.digikala.data.remote.NetworkResult
import ir.truelearn.digikala.repository.HomeRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel(){

    val slider = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())


    suspend fun getSlider(){
        viewModelScope.launch {
            slider.emit(repository.getSlider())
        }
    }

}