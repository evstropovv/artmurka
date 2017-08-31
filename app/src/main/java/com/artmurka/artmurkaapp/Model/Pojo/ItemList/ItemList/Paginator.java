
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paginator {

    @SerializedName("cur_page")
    @Expose
    private String curPage;
    @SerializedName("num_pages")
    @Expose
    private int numPages;

    public String getCurPage() {
        return curPage;
    }

    public void setCurPage(String curPage) {
        this.curPage = curPage;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

}
