package com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datum {

    @SerializedName("TotalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("Addresses")
    @Expose
    private List<Address> addresses = null;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

}