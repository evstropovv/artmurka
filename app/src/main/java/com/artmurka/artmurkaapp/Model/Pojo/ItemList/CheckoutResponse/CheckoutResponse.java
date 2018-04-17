package com.artmurka.artmurkaapp.Model.Pojo.ItemList.CheckoutResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckoutResponse {

    @SerializedName("success")
    @Expose
    private Success success;

    @SerializedName("error")
    @Expose
    private Error error;

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }

    public CheckoutResponse withSuccess(Success success) {
        this.success = success;
        return this;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public CheckoutResponse withError(Error error) {
        this.error = error;
        return this;
    }
}