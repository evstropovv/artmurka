
package com.artmurka.artmurkaapp.data.model.pojo.itemlist.good;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryRating {

    @SerializedName("rated")
    @Expose
    private String rated;
    @SerializedName("rating_num_float")
    @Expose
    private Integer ratingNumFloat;
    @SerializedName("rating_num")
    @Expose
    private String ratingNum;
    @SerializedName("rating")
    @Expose
    private String rating;

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public Integer getRatingNumFloat() {
        return ratingNumFloat;
    }

    public void setRatingNumFloat(Integer ratingNumFloat) {
        this.ratingNumFloat = ratingNumFloat;
    }

    public String getRatingNum() {
        return ratingNum;
    }

    public void setRatingNum(String ratingNum) {
        this.ratingNum = ratingNum;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}
