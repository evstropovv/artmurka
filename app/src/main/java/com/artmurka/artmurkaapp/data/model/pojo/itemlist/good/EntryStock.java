
package com.artmurka.artmurkaapp.data.model.pojo.itemlist.good;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryStock {

    @SerializedName("stock")
    @Expose
    private String stock;
    @SerializedName("stock_total")
    @Expose
    private String stockTotal;

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(String stockTotal) {
        this.stockTotal = stockTotal;
    }

}
