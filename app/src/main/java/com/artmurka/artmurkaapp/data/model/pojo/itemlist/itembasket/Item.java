
package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("entry_is_in_discount")
    @Expose
    private int entryIsInDiscount;
    @SerializedName("entry_others")
    @Expose
    private EntryOthers entryOthers;
    @SerializedName("entry_price_in")
    @Expose
    private EntryPriceIn entryPriceIn;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("entry_title")
    @Expose
    private String entryTitle;
    @SerializedName("entry_price_vat")
    @Expose
    private EntryPriceVat entryPriceVat;
    @SerializedName("entry_options")
    @Expose
    private String entryOptions;
    @SerializedName("entry_add_uid")
    @Expose
    private String entryAddUid;
    @SerializedName("cnt")
    @Expose
    private String cnt;
    @SerializedName("entry_cat_id")
    @Expose
    private String entryCatId;
    @SerializedName("entry_weight")
    @Expose
    private EntryWeight entryWeight;
    @SerializedName("entry_art_no")
    @Expose
    private String entryArtNo;
    @SerializedName("entry_shop_url")
    @Expose
    private String entryShopUrl;
    @SerializedName("entry_unit")
    @Expose
    private String entryUnit;
    @SerializedName("entry_is_in_wishlist")
    @Expose
    private int entryIsInWishlist;
    @SerializedName("entry_id")
    @Expose
    private String entryId;
    @SerializedName("summ")
    @Expose
    private Summ summ;
    @SerializedName("entry_photo")
    @Expose
    private EntryPhoto entryPhoto;
    @SerializedName("entry_warr")
    @Expose
    private String entryWarr;
    @SerializedName("over")
    @Expose
    private Over over;
    @SerializedName("entry_hgu")
    @Expose
    private String entryHgu;
    @SerializedName("entry_brand")
    @Expose
    private String entryBrand;
    @SerializedName("entry_add_user")
    @Expose
    private String entryAddUser;
    @SerializedName("entry_type")
    @Expose
    private String entryType;
    @SerializedName("entry_price_vat_eval")
    @Expose
    private EntryPriceVatEval entryPriceVatEval;
    @SerializedName("entry_price")
    @Expose
    private EntryPrice entryPrice;

    public int getEntryIsInDiscount() {
        return entryIsInDiscount;
    }

    public void setEntryIsInDiscount(int entryIsInDiscount) {
        this.entryIsInDiscount = entryIsInDiscount;
    }

    public EntryOthers getEntryOthers() {
        return entryOthers;
    }

    public void setEntryOthers(EntryOthers entryOthers) {
        this.entryOthers = entryOthers;
    }

    public EntryPriceIn getEntryPriceIn() {
        return entryPriceIn;
    }

    public void setEntryPriceIn(EntryPriceIn entryPriceIn) {
        this.entryPriceIn = entryPriceIn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntryTitle() {
        return entryTitle;
    }

    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }

    public EntryPriceVat getEntryPriceVat() {
        return entryPriceVat;
    }

    public void setEntryPriceVat(EntryPriceVat entryPriceVat) {
        this.entryPriceVat = entryPriceVat;
    }

    public String getEntryOptions() {
        return entryOptions;
    }

    public void setEntryOptions(String entryOptions) {
        this.entryOptions = entryOptions;
    }

    public String getEntryAddUid() {
        return entryAddUid;
    }

    public void setEntryAddUid(String entryAddUid) {
        this.entryAddUid = entryAddUid;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public String getEntryCatId() {
        return entryCatId;
    }

    public void setEntryCatId(String entryCatId) {
        this.entryCatId = entryCatId;
    }

    public EntryWeight getEntryWeight() {
        return entryWeight;
    }

    public void setEntryWeight(EntryWeight entryWeight) {
        this.entryWeight = entryWeight;
    }

    public String getEntryArtNo() {
        return entryArtNo;
    }

    public void setEntryArtNo(String entryArtNo) {
        this.entryArtNo = entryArtNo;
    }

    public String getEntryShopUrl() {
        return entryShopUrl;
    }

    public void setEntryShopUrl(String entryShopUrl) {
        this.entryShopUrl = entryShopUrl;
    }

    public String getEntryUnit() {
        return entryUnit;
    }

    public void setEntryUnit(String entryUnit) {
        this.entryUnit = entryUnit;
    }

    public int getEntryIsInWishlist() {
        return entryIsInWishlist;
    }

    public void setEntryIsInWishlist(int entryIsInWishlist) {
        this.entryIsInWishlist = entryIsInWishlist;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public Summ getSumm() {
        return summ;
    }

    public void setSumm(Summ summ) {
        this.summ = summ;
    }

    public EntryPhoto getEntryPhoto() {
        return entryPhoto;
    }

    public void setEntryPhoto(EntryPhoto entryPhoto) {
        this.entryPhoto = entryPhoto;
    }

    public String getEntryWarr() {
        return entryWarr;
    }

    public void setEntryWarr(String entryWarr) {
        this.entryWarr = entryWarr;
    }

    public Over getOver() {
        return over;
    }

    public void setOver(Over over) {
        this.over = over;
    }

    public String getEntryHgu() {
        return entryHgu;
    }

    public void setEntryHgu(String entryHgu) {
        this.entryHgu = entryHgu;
    }

    public String getEntryBrand() {
        return entryBrand;
    }

    public void setEntryBrand(String entryBrand) {
        this.entryBrand = entryBrand;
    }

    public String getEntryAddUser() {
        return entryAddUser;
    }

    public void setEntryAddUser(String entryAddUser) {
        this.entryAddUser = entryAddUser;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public EntryPriceVatEval getEntryPriceVatEval() {
        return entryPriceVatEval;
    }

    public void setEntryPriceVatEval(EntryPriceVatEval entryPriceVatEval) {
        this.entryPriceVatEval = entryPriceVatEval;
    }

    public EntryPrice getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(EntryPrice entryPrice) {
        this.entryPrice = entryPrice;
    }

}
