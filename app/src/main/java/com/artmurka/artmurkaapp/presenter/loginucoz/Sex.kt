package com.artmurka.artmurkaapp.presenter.loginucoz

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sex : Serializable {

    @SerializedName("label")
    @Expose
    var label: String? = null
    @SerializedName("code")
    @Expose
    var code: String? = null

    companion object {
        private const val serialVersionUID = -4580800409709480711L
    }

}