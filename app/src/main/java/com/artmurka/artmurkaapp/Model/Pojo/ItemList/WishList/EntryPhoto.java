
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.WishList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryPhoto {


    @SerializedName("num_photos")
    @Expose
    private Long numPhotos;
    @SerializedName("def_photo")
    @Expose
    private DefPhoto defPhoto;

    public Long getNumPhotos() {
        return numPhotos;
    }

    public void setNumPhotos(Long numPhotos) {
        this.numPhotos = numPhotos;
    }

    public DefPhoto getDefPhoto() {
        return defPhoto;
    }

    public void setDefPhoto(DefPhoto defPhoto) {
        this.defPhoto = defPhoto;
    }

}
