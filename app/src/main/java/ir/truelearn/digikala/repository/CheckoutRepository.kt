package ir.truelearn.digikala.repository

import ir.truelearn.digikala.data.model.checkout.OrderDetail
import ir.truelearn.digikala.data.model.home.AmazingItem
import ir.truelearn.digikala.data.model.home.MainCategory
import ir.truelearn.digikala.data.model.home.Slider
import ir.truelearn.digikala.data.model.home.StoreProduct
import ir.truelearn.digikala.data.model.prfile.LoginRequest
import ir.truelearn.digikala.data.model.prfile.LoginResponse
import ir.truelearn.digikala.data.remote.*
import javax.inject.Inject

class CheckoutRepository @Inject constructor(private val api: CheckoutApiInterface) : BaseApiResponse() {

    suspend fun getShippingCost(address: String): NetworkResult<Int> =
        safeApiCall {
           api.getShippingCost(address)
        }


    suspend fun setNewOrder(cartOrderDetail: OrderDetail): NetworkResult<String> =
        safeApiCall {
            api.setNewOrder(cartOrderDetail)
        }

}