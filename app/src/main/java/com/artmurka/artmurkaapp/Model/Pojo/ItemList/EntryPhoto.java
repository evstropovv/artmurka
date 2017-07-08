
package com.artmurka.artmurkaapp.Model.Pojo.ItemList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryPhoto {

    @SerializedName("def_photo")
    @Expose
    private DefPhoto defPhoto;
    @SerializedName("num_photos")
    @Expose
    private int numPhotos;
//    @SerializedName("others_photo")
//    @Expose
//    private OthersPhoto othersPhoto;

    public DefPhoto getDefPhoto() {
        return defPhoto;
    }

    public void setDefPhoto(DefPhoto defPhoto) {
        this.defPhoto = defPhoto;
    }

    public int getNumPhotos() {
        return numPhotos;
    }

    public void setNumPhotos(int numPhotos) {
        this.numPhotos = numPhotos;
    }

//    public OthersPhoto getOthersPhoto() {
//        return othersPhoto;
//    }
//
//    public void setOthersPhoto(OthersPhoto othersPhoto) {
//        this.othersPhoto = othersPhoto;
//    }

}
