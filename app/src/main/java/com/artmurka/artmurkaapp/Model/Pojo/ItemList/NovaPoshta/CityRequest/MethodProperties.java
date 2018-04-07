package com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityRequest;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MethodProperties {

    @SerializedName("CityName")
    @Expose
    private String cityRef;

    @SerializedName("Page")
    @Expose
    private String page;

    @SerializedName("Warehouse")
    @Expose
    private String warehouse;

    public MethodProperties(String cityRef) {
        this.cityRef = cityRef;
        this.page = "1";
        this.warehouse = "1";
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getCityRef() {
        return cityRef;
    }

    public void setCityRef(String area) {
        this.cityRef = area;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }
}