package com.artmurka.artmurkaapp.data.model.pojo.itemlist.formorder;

//Необходим при заказе
//для Полей емейл и адрес;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdrAndEmail {

    @SerializedName("fld2")
    @Expose
    String fld2;

    @SerializedName("fld3")
    @Expose
    String fld3;

    public AdrAndEmail(String fld2, String fld3){
        this.fld2 = fld2;
        this.fld3 = fld3;
    }
}
