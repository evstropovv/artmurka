package com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.WarehousesRequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MethodProperties(@field:SerializedName("CityRef")
                       @field:Expose
                       var settlementRef: String?) {

    fun withSettlementRef(settlementRef: String): MethodProperties {
        this.settlementRef = settlementRef
        return this
    }

}
