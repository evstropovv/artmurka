package com.artmurka.artmurkaapp.presenter.loginucoz

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User : Serializable {

    @SerializedName("user_profile_link")
    @Expose
    var userProfileLink: String? = null
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("uid")
    @Expose
    var uid: Long? = null
    @SerializedName("nickname")
    @Expose
    var nickname: String? = null
    @SerializedName("avatar")
    @Expose
    var avatar: String? = null
    @SerializedName("sex")
    @Expose
    var sex: Sex? = null
    @SerializedName("last_name")
    @Expose
    var lastName: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("city")
    @Expose
    var city: String? = null
    @SerializedName("birthday")
    @Expose
    var birthday: String? = null
    @SerializedName("first_name")
    @Expose
    var firstName: String? = null

    companion object {
        private const val serialVersionUID = -6279592148051098585L
    }

}