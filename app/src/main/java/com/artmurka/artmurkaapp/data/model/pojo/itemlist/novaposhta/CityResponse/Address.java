package com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.CityResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("Warehouses")
    @Expose
    private Integer warehouses;
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
    @SerializedName("StreetsAvailability")
    @Expose
    private Boolean streetsAvailability;

    public Integer getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Integer warehouses) {
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

    public Boolean getStreetsAvailability() {
        return streetsAvailability;
    }

    public void setStreetsAvailability(Boolean streetsAvailability) {
        this.streetsAvailability = streetsAvailability;
    }

}