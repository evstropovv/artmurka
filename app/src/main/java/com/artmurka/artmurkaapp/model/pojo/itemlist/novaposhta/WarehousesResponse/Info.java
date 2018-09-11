
package com.artmurka.artmurkaapp.model.pojo.itemlist.novaposhta.WarehousesResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("totalCount")
    @Expose
    private String totalCount;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public Info withTotalCount(String totalCount) {
        this.totalCount = totalCount;
        return this;
    }

}
