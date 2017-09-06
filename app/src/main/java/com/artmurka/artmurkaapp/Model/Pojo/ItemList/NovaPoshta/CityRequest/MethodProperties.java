package com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityRequest;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MethodProperties {

    @SerializedName("CityName")
    @Expose
    private String cityName;
    @SerializedName("Limit")
    @Expose
    private Integer limit;
    public MethodProperties(String cityName, Integer limit){
        this.cityName = cityName;
        this.limit = limit;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

}