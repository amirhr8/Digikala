package ir.truelearn.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.truelearn.digikala.data.model.home.AmazingItem
import ir.truelearn.digikala.data.model.home.Slider
import ir.truelearn.digikala.data.remote.NetworkResult
import ir.truelearn.digikala.repository.HomeRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val slider = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val amazingItems = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val superMarketItems = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val banners = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())


    suspend fun getAllDataFromServer() {
        viewModelScope.launch {

            //fire and forget
            launch {
                slider.emit(repository.getSlider())
            }

            launch {
                amazingItems.emit(repository.getAmazingItems())
            }

            launch {
                superMarketItems.emit(repository.getAmazingSuperMarketItems())
            }

            launch {
                banners.emit(repository.getProposalBanners())
            }

        }
    }

}