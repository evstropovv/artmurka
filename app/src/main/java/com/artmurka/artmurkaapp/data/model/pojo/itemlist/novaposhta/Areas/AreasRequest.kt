package com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.Areas


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AreasRequest {


    @SerializedName("apiKey")
    @Expose
    var apiKey: String? = null

    @SerializedName("modelName")
    @Expose
    var modelName: String? = null

    @SerializedName("calledMethod")
    @Expose
    var calledMethod: String? = null

    //    public MethodProperties getMethodProperties() {
    //        return methodProperties;
    //    }
    //
    //    public void setMethodProperties(MethodProperties methodProperties) {
    //        this.methodProperties = methodProperties;
    //    }
}
