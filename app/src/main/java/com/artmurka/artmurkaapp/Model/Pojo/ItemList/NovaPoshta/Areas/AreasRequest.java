package com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.Areas;


import com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityRequest.MethodProperties;
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


    //commit method proPerties TODO
    @SerializedName("methodProperties")
    @Expose
    private MethodProperties methodProperties;

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

    public MethodProperties getMethodProperties() {
        return methodProperties;
    }

    public void setMethodProperties(MethodProperties methodProperties) {
        this.methodProperties = methodProperties;
    }
}
