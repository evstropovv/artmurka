
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.AboutGoods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class _5 {

    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("small")
    @Expose
    private String small;
    @SerializedName("thumb")
    @Expose
    private String thumb;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

}
