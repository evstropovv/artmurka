
package com.artmurka.artmurkaapp.model.pojo.itemlist.good;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryPhoto {

    @SerializedName("others_photo")
    @Expose
    private String othersPhoto;
    @SerializedName("def_photo")
    @Expose
    private DefPhoto defPhoto;
    @SerializedName("num_photos")
    @Expose
    private Integer numPhotos;

    public String getOthersPhoto() {
        return othersPhoto;
    }

    public void setOthersPhoto(String othersPhoto) {
        this.othersPhoto = othersPhoto;
    }

    public DefPhoto getDefPhoto() {
        return defPhoto;
    }

    public void setDefPhoto(DefPhoto defPhoto) {
        this.defPhoto = defPhoto;
    }

    public Integer getNumPhotos() {
        return numPhotos;
    }

    public void setNumPhotos(Integer numPhotos) {
        this.numPhotos = numPhotos;
    }

}
