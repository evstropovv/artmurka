
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Areas;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Areas {

    @SerializedName("success")
    @Expose
    private boolean success;
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
    private Info info;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
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

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
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
