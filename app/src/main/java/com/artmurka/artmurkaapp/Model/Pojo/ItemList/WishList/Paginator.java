
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.WishList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paginator {

    @SerializedName("cur_page")
    @Expose
    private Long curPage;
    @SerializedName("num_pages")
    @Expose
    private Long numPages;

    public Long getCurPage() {
        return curPage;
    }

    public void setCurPage(Long curPage) {
        this.curPage = curPage;
    }

    public Long getNumPages() {
        return numPages;
    }

    public void setNumPages(Long numPages) {
        this.numPages = numPages;
    }

}
