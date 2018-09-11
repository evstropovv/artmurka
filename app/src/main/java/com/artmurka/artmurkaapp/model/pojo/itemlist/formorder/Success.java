package com.artmurka.artmurkaapp.model.pojo.itemlist.formorder;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Success {


    @SerializedName("msg")
    @Expose
    private final ThreadLocal<String> msg = new ThreadLocal<>();

    public String getMsg() {
        return msg.get();
    }

    public void setMsg(String msg) {
        this.msg.set(msg);
    }
}
