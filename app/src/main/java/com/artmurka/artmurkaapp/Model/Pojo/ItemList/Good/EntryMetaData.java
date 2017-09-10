
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Good;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryMetaData {

    @SerializedName("meta_title")
    @Expose
    private String metaTitle;
    @SerializedName("meta_description")
    @Expose
    private String metaDescription;

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

}
