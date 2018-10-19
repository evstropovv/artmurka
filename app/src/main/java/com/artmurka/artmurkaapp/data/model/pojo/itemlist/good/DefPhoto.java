
package com.artmurka.artmurkaapp.data.model.pojo.itemlist.good;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DefPhoto {

    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("small")
    @Expose
    private String small;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

}