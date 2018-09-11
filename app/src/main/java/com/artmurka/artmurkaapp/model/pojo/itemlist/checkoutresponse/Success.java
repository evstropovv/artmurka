package com.artmurka.artmurkaapp.model.pojo.itemlist.checkoutresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Success {

    @SerializedName("msg")
    @Expose
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Success withMsg(String msg) {
        this.msg = msg;
        return this;
    }

}