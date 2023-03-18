package ir.truelearn.digikala.repository

import ir.truelearn.digikala.data.remote.BaseApiResponse
import ir.truelearn.digikala.data.remote.BasketApiInterface
import javax.inject.Inject


class BasketRepository @Inject constructor(private val api: BasketApiInterface) : BaseApiResponse() {



}