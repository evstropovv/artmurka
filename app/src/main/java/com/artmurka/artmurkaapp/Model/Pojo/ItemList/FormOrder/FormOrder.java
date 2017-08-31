package com.artmurka.artmurkaapp.Model.Pojo.ItemList.FormOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormOrder {

    @SerializedName("success")
    @Expose
    private Success success;

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }

}
