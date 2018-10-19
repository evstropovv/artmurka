package com.artmurka.artmurkaapp.data.model.pojo.itemlist.formorder


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Success {


    @SerializedName("msg")
    @Expose
    private val msg = ThreadLocal<String>()

    fun getMsg(): String {
        return msg.get()
    }

    fun setMsg(msg: String) {
        this.msg.set(msg)
    }
}
