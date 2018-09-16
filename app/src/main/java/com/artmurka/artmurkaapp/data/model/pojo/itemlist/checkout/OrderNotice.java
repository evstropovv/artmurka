
package com.artmurka.artmurkaapp.data.model.pojo.itemlist.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderNotice {

    @SerializedName("notice_err")
    @Expose
    private String noticeErr;
    @SerializedName("notice")
    @Expose
    private String notice;

    public String getNoticeErr() {
        return noticeErr;
    }

    public void setNoticeErr(String noticeErr) {
        this.noticeErr = noticeErr;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

}
