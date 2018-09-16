package com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.Areas;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AreasRequest {


    @SerializedName("apiKey")
    @Expose
    private String apiKey;

    @SerializedName("modelName")
    @Expose
    private String modelName;

    @SerializedName("calledMethod")
    @Expose
    private String calledMethod;


    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getCalledMethod() {
        return calledMethod;
    }

    public void setCalledMethod(String calledMethod) {
        this.calledMethod = calledMethod;
    }

//    public MethodProperties getMethodProperties() {
//        return methodProperties;
//    }
//
//    public void setMethodProperties(MethodProperties methodProperties) {
//        this.methodProperties = methodProperties;
//    }
}
