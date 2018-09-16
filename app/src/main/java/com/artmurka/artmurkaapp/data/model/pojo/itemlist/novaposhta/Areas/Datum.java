package com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.Areas;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("Ref")
    @Expose
    private String ref;
    @SerializedName("AreasCenter")
    @Expose
    private String areasCenter;
    @SerializedName("Description")
    @Expose
    private String description;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAreasCenter() {
        return areasCenter;
    }

    public void setAreasCenter(String areasCenter) {
        this.areasCenter = areasCenter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}