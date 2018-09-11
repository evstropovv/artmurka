package com.artmurka.artmurkaapp.presenter.loginucoz;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sex implements Serializable
{

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("code")
    @Expose
    private String code;
    private final static long serialVersionUID = -4580800409709480711L;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}