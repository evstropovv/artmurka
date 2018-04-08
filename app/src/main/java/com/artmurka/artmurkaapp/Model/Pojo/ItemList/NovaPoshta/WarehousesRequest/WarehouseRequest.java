
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.WarehousesRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WarehouseRequest {

    @SerializedName("modelName")
    @Expose
    private String modelName;
    @SerializedName("calledMethod")
    @Expose
    private String calledMethod;
    @SerializedName("methodProperties")
    @Expose
    private MethodProperties methodProperties;
    @SerializedName("apiKey")
    @Expose
    private String apiKey;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public WarehouseRequest withModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getCalledMethod() {
        return calledMethod;
    }

    public void setCalledMethod(String calledMethod) {
        this.calledMethod = calledMethod;
    }

    public WarehouseRequest withCalledMethod(String calledMethod) {
        this.calledMethod = calledMethod;
        return this;
    }

    public MethodProperties getMethodProperties() {
        return methodProperties;
    }

    public void setMethodProperties(MethodProperties methodProperties) {
        this.methodProperties = methodProperties;
    }

    public WarehouseRequest withMethodProperties(MethodProperties methodProperties) {
        this.methodProperties = methodProperties;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public WarehouseRequest withApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

}
