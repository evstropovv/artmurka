
package com.artmurka.artmurkaapp.Model.Pojo.ItemList;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sort {

    @SerializedName("order")
    @Expose
    private String order;
    @SerializedName("slist")
    @Expose
    private List<Slist> slist = null;
    @SerializedName("sort")
    @Expose
    private String sort;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List<Slist> getSlist() {
        return slist;
    }

    public void setSlist(List<Slist> slist) {
        this.slist = slist;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

}
