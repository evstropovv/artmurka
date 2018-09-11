
package com.artmurka.artmurkaapp.model.pojo.itemlist.aboutgoods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AboutGood {

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
