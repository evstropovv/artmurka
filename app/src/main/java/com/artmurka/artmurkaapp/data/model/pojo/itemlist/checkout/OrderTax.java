
package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderTax {

    @SerializedName("tax_raw")
    @Expose
    private Long taxRaw;
    @SerializedName("tax")
    @Expose
    private Long tax;

    public Long getTaxRaw() {
        return taxRaw;
    }

    public void setTaxRaw(Long taxRaw) {
        this.taxRaw = taxRaw;
    }

    public Long getTax() {
        return tax;
    }

    public void setTax(Long tax) {
        this.tax = tax;
    }

}
