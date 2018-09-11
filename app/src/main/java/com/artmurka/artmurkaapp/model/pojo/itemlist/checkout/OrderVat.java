
package com.artmurka.artmurkaapp.model.pojo.itemlist.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderVat {

    @SerializedName("vat_raw")
    @Expose
    private Long vatRaw;
    @SerializedName("vat_type")
    @Expose
    private Long vatType;
    @SerializedName("vat")
    @Expose
    private Long vat;

    public Long getVatRaw() {
        return vatRaw;
    }

    public void setVatRaw(Long vatRaw) {
        this.vatRaw = vatRaw;
    }

    public Long getVatType() {
        return vatType;
    }

    public void setVatType(Long vatType) {
        this.vatType = vatType;
    }

    public Long getVat() {
        return vat;
    }

    public void setVat(Long vat) {
        this.vat = vat;
    }

}
