
package com.artmurka.artmurkaapp.model.pojo.itemlist.aboutgoods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class EntryPhoto {

    @SerializedName("others_photo")
    @Expose
    private final HashMap<String, SizePhoto> othersPhoto = null;

    @SerializedName("def_photo")
    @Expose
    private DefPhoto defPhoto;

    @SerializedName("num_photos")
    @Expose
    private int numPhotos;

    public HashMap<String, SizePhoto> getOthersPhoto() {
        return othersPhoto;
    }

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

}
