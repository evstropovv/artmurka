
package com.artmurka.artmurkaapp.model.pojo.itemlist.wishList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryStock {

    @SerializedName("stock_total")
    @Expose
    private String stockTotal;
    @SerializedName("stock")
    @Expose
    private String stock;

    public String getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(String stockTotal) {
        this.stockTotal = stockTotal;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

}
