package com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.Areas;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AreasResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("errors")
    @Expose
    private List<Object> errors = null;
    @SerializedName("warnings")
    @Expose
    private List<Object> warnings = null;
    @SerializedName("info")
    @Expose
    private List<Object> info = null;
    @SerializedName("messageCodes")
    @Expose
    private List<Object> messageCodes = null;
    @SerializedName("errorCodes")
    @Expose
    private List<Object> errorCodes = null;
    @SerializedName("warningCodes")
    @Expose
    private List<Object> warningCodes = null;
    @SerializedName("infoCodes")
    @Expose
    private List<Object> infoCodes = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public List<Object> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<Object> warnings) {
        this.warnings = warnings;
    }

    public List<Object> getInfo() {
        return info;
    }

    public void setInfo(List<Object> info) {
        this.info = info;
    }

    public List<Object> getMessageCodes() {
        return messageCodes;
    }

    public void setMessageCodes(List<Object> messageCodes) {
        this.messageCodes = messageCodes;
    }

    public List<Object> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(List<Object> errorCodes) {
        this.errorCodes = errorCodes;
    }

    public List<Object> getWarningCodes() {
        return warningCodes;
    }

    public void setWarningCodes(List<Object> warningCodes) {
        this.warningCodes = warningCodes;
    }

    public List<Object> getInfoCodes() {
        return infoCodes;
    }

    public void setInfoCodes(List<Object> infoCodes) {
        this.infoCodes = infoCodes;
    }

}
