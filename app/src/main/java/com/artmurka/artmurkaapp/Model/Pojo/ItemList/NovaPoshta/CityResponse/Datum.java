package com.artmurka.artmurkaapp.Model.Pojo.ItemList.NovaPoshta.CityResponse;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("TotalCount")
    @Expose
    private Long totalCount;
    @SerializedName("Addresses")
    @Expose
    private List<Address> addresses = null;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

}