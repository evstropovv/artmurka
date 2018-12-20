package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkoutresponse

import com.google.gson.annotations.SerializedName

data class CheckoutResponse(
        @SerializedName("success")
        val success: Success?,
        @SerializedName("error")
        val error: Error?

) {
    data class Success(
            @SerializedName("msg")
            val msg: String?
    )
    data class Error(
            @SerializedName("msg")
            val msg: String?
    )
}

