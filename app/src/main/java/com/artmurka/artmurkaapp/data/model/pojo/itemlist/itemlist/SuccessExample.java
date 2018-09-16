
package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itemlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuccessExample {

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
