package com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("Warehouses")
    @Expose
    private Long warehouses;
    @SerializedName("MainDescription")
    @Expose
    private String mainDescription;
    @SerializedName("Area")
    @Expose
    private String area;
    @SerializedName("Region")
    @Expose
    private String region;
    @SerializedName("SettlementTypeCode")
    @Expose
    private String settlementTypeCode;
    @SerializedName("Ref")
    @Expose
    private String ref;
    @SerializedName("DeliveryCity")
    @Expose
    private String deliveryCity;

    public Long getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Long warehouses) {
        this.warehouses = warehouses;
    }

    public String getMainDescription() {
        return mainDescription;
    }

    public void setMainDescription(String mainDescription) {
        this.mainDescription = mainDescription;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSettlementTypeCode() {
        return settlementTypeCode;
    }

    public void setSettlementTypeCode(String settlementTypeCode) {
        this.settlementTypeCode = settlementTypeCode;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

}
