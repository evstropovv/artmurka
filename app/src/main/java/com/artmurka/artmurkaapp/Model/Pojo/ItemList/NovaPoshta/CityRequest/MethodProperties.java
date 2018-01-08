package com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityRequest;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MethodProperties {

    //    @SerializedName("CityName")
    @SerializedName("AreasCenter")
    @Expose
    private String area;

    @SerializedName("Page")
    @Expose
    private String page;

    @SerializedName("Warehouse")
    @Expose
    private String warehouse;

    public MethodProperties(String area) {
        this.area = area;
        this.page = "1";
        this.warehouse = "1";
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }
}