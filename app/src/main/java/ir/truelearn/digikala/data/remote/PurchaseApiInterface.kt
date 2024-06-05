package ir.truelearn.digikala.data.remote

import ir.truelearn.digikala.data.model.ResponseResult
import ir.truelearn.digikala.data.model.checkout.OrderFullDetail
import ir.truelearn.digikala.data.model.home.AmazingItem
import ir.truelearn.digikala.data.model.home.MainCategory
import ir.truelearn.digikala.data.model.home.Slider
import ir.truelearn.digikala.data.model.home.StoreProduct
import ir.truelearn.digikala.data.model.prfile.LoginRequest
import ir.truelearn.digikala.data.model.prfile.LoginResponse
import ir.truelearn.digikala.data.model.prfile.SetUserNameRequest
import ir.truelearn.digikala.data.model.purchase.PaymentRequest
import ir.truelearn.digikala.data.model.purchase.PaymentResponse
import ir.truelearn.digikala.data.model.purchase.PaymentVerificationRequest
import ir.truelearn.digikala.data.model.purchase.PaymentVerificationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PurchaseApiInterface {

    @POST("request.json")
    suspend fun startPurchase(
        @Body paymentRequest: PaymentRequest
    ) : Response<PaymentResponse>


    @POST("verify.json")
    suspend fun verifyPurchase(
        @Body paymentVerificationRequest: PaymentVerificationRequest
    ) : Response<PaymentVerificationResponse>


}