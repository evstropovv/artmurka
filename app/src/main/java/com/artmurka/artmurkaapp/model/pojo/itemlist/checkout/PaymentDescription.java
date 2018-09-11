package com.artmurka.artmurkaapp.model.pojo.itemlist.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PaymentDescription {

    @SerializedName("pos")
    @Expose
    private String pos;

    @SerializedName("dscr")
    @Expose
    private String dscr;

    @SerializedName("name")
    @Expose
    private String name;

    public String getPos(){
        return pos;
    }
    public String getDscr(){
        return dscr;
    }
    public String getName(){
        return name;
    }

    public void setPos(String pos){
        this.pos = pos;
    }

    public void setDscr(String dscr){
        this.dscr = dscr;
    }
   public void setName(String name){
        this.name = name;
    }


}
