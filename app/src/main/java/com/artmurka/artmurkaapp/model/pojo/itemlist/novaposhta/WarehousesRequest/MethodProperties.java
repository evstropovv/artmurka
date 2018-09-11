
package com.artmurka.artmurkaapp.model.pojo.itemlist.novaposhta.WarehousesRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MethodProperties {

    @SerializedName("CityRef")
    @Expose
    private String settlementRef;

    public MethodProperties(String settlementRef){
        this.settlementRef = settlementRef;
    }

    public String getSettlementRef() {
        return settlementRef;
    }

    public void setSettlementRef(String settlementRef) {
        this.settlementRef = settlementRef;
    }

    public MethodProperties withSettlementRef(String settlementRef) {
        this.settlementRef = settlementRef;
        return this;
    }

}
