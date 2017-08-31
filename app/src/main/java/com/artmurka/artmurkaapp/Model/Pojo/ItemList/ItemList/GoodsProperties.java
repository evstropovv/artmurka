
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.ItemList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoodsProperties {

    @SerializedName("entry_cat")
    @Expose
    private EntryCat entryCat;

    @SerializedName("entry_file_size")
    @Expose
    private int entryFileSize;
    @SerializedName("entry_art_no")
    @Expose
    private String entryArtNo;
    @SerializedName("entry_price_vat_eval")
    @Expose
    private EntryPriceVatEval entryPriceVatEval;
    @SerializedName("entry_cats")
    @Expose
    private EntryCats entryCats;
    @SerializedName("entry_modified_time")
    @Expose
    private String entryModifiedTime;
    @SerializedName("entry_is_in_wishlist")
    @Expose
    private int entryIsInWishlist;
    @SerializedName("entry_type")
    @Expose
    private String entryType;
    @SerializedName("entry_solds")
    @Expose
    private String entrySolds;
    @SerializedName("entry_unit")
    @Expose
    private String entryUnit;
    @SerializedName("entry_add_gid")
    @Expose
    private int entryAddGid;

    @SerializedName("entry_price_in")
    @Expose
    private EntryPriceIn entryPriceIn;
    @SerializedName("entry_warr")
    @Expose
    private String entryWarr;
    @SerializedName("entry_photo")
    @Expose
    private EntryPhoto entryPhoto;
    @SerializedName("entry_add_user")
    @Expose
    private String entryAddUser;
    @SerializedName("entry_price_vat")
    @Expose
    private EntryPriceVat entryPriceVat;
    @SerializedName("entry_hgu")
    @Expose
    private String entryHgu;
    @SerializedName("entry_is_hidden")
    @Expose
    private String entryIsHidden;
    @SerializedName("entry_rating")
    @Expose
    private EntryRating entryRating;
    @SerializedName("entry_views")
    @Expose
    private String entryViews;
    @SerializedName("entry_added_time")
    @Expose
    private String entryAddedTime;
    @SerializedName("entry_description")
    @Expose
    private String entryDescription;
    @SerializedName("entry_ordered")
    @Expose
    private String entryOrdered;
    @SerializedName("entry_id")
    @Expose
    private String entryId;
    @SerializedName("entry_is_in_basket")
    @Expose
    private int entryIsInBasket;
    @SerializedName("entry_brand")
    @Expose
    private String entryBrand;
    @SerializedName("entry_title")
    @Expose
    private String entryTitle;
    @SerializedName("entry_price")
    @Expose
    private EntryPrice entryPrice;
    @SerializedName("entry_brief")
    @Expose
    private String entryBrief;
    @SerializedName("num_com")
    @Expose
    private String numCom;
    @SerializedName("entry_is_in_discount")
    @Expose
    private int entryIsInDiscount;
    @SerializedName("entry_stock")
    @Expose
    private EntryStock entryStock;
    @SerializedName("entry_add_uid")
    @Expose
    private String entryAddUid;
    @SerializedName("entry_others")
    @Expose
    private EntryOthers entryOthers;
    @SerializedName("entry_shop_url")
    @Expose
    private String entryShopUrl;
    @SerializedName("entry_price_old")
    @Expose
    private EntryPriceOld entryPriceOld;

    public EntryCat getEntryCat() {
        return entryCat;
    }

    public void setEntryCat(EntryCat entryCat) {
        this.entryCat = entryCat;
    }

    public int getEntryFileSize() {
        return entryFileSize;
    }

    public void setEntryFileSize(int entryFileSize) {
        this.entryFileSize = entryFileSize;
    }

    public String getEntryArtNo() {
        return entryArtNo;
    }

    public void setEntryArtNo(String entryArtNo) {
        this.entryArtNo = entryArtNo;
    }

    public EntryPriceVatEval getEntryPriceVatEval() {
        return entryPriceVatEval;
    }

    public void setEntryPriceVatEval(EntryPriceVatEval entryPriceVatEval) {
        this.entryPriceVatEval = entryPriceVatEval;
    }

    public EntryCats getEntryCats() {
        return entryCats;
    }

    public void setEntryCats(EntryCats entryCats) {
        this.entryCats = entryCats;
    }

    public String getEntryModifiedTime() {
        return entryModifiedTime;
    }

    public void setEntryModifiedTime(String entryModifiedTime) {
        this.entryModifiedTime = entryModifiedTime;
    }

    public int getEntryIsInWishlist() {
        return entryIsInWishlist;
    }

    public void setEntryIsInWishlist(int entryIsInWishlist) {
        this.entryIsInWishlist = entryIsInWishlist;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public String getEntrySolds() {
        return entrySolds;
    }

    public void setEntrySolds(String entrySolds) {
        this.entrySolds = entrySolds;
    }

    public String getEntryUnit() {
        return entryUnit;
    }

    public void setEntryUnit(String entryUnit) {
        this.entryUnit = entryUnit;
    }

    public int getEntryAddGid() {
        return entryAddGid;
    }

    public void setEntryAddGid(int entryAddGid) {
        this.entryAddGid = entryAddGid;
    }


    public EntryPriceIn getEntryPriceIn() {
        return entryPriceIn;
    }

    public void setEntryPriceIn(EntryPriceIn entryPriceIn) {
        this.entryPriceIn = entryPriceIn;
    }

    public String getEntryWarr() {
        return entryWarr;
    }

    public void setEntryWarr(String entryWarr) {
        this.entryWarr = entryWarr;
    }

    public EntryPhoto getEntryPhoto() {
        return entryPhoto;
    }

    public void setEntryPhoto(EntryPhoto entryPhoto) {
        this.entryPhoto = entryPhoto;
    }

    public String getEntryAddUser() {
        return entryAddUser;
    }

    public void setEntryAddUser(String entryAddUser) {
        this.entryAddUser = entryAddUser;
    }

    public EntryPriceVat getEntryPriceVat() {
        return entryPriceVat;
    }

    public void setEntryPriceVat(EntryPriceVat entryPriceVat) {
        this.entryPriceVat = entryPriceVat;
    }

    public String getEntryHgu() {
        return entryHgu;
    }

    public void setEntryHgu(String entryHgu) {
        this.entryHgu = entryHgu;
    }

    public String getEntryIsHidden() {
        return entryIsHidden;
    }

    public void setEntryIsHidden(String entryIsHidden) {
        this.entryIsHidden = entryIsHidden;
    }

    public EntryRating getEntryRating() {
        return entryRating;
    }

    public void setEntryRating(EntryRating entryRating) {
        this.entryRating = entryRating;
    }

    public String getEntryViews() {
        return entryViews;
    }

    public void setEntryViews(String entryViews) {
        this.entryViews = entryViews;
    }

    public String getEntryAddedTime() {
        return entryAddedTime;
    }

    public void setEntryAddedTime(String entryAddedTime) {
        this.entryAddedTime = entryAddedTime;
    }

    public String getEntryDescription() {
        return entryDescription;
    }

    public void setEntryDescription(String entryDescription) {
        this.entryDescription = entryDescription;
    }

    public String getEntryOrdered() {
        return entryOrdered;
    }

    public void setEntryOrdered(String entryOrdered) {
        this.entryOrdered = entryOrdered;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public int getEntryIsInBasket() {
        return entryIsInBasket;
    }

    public void setEntryIsInBasket(int entryIsInBasket) {
        this.entryIsInBasket = entryIsInBasket;
    }

    public String getEntryBrand() {
        return entryBrand;
    }

    public void setEntryBrand(String entryBrand) {
        this.entryBrand = entryBrand;
    }

    public String getEntryTitle() {
        return entryTitle;
    }

    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }

    public EntryPrice getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(EntryPrice entryPrice) {
        this.entryPrice = entryPrice;
    }

    public String getEntryBrief() {
        return entryBrief;
    }

    public void setEntryBrief(String entryBrief) {
        this.entryBrief = entryBrief;
    }

    public String getNumCom() {
        return numCom;
    }

    public void setNumCom(String numCom) {
        this.numCom = numCom;
    }

    public int getEntryIsInDiscount() {
        return entryIsInDiscount;
    }

    public void setEntryIsInDiscount(int entryIsInDiscount) {
        this.entryIsInDiscount = entryIsInDiscount;
    }

    public EntryStock getEntryStock() {
        return entryStock;
    }

    public void setEntryStock(EntryStock entryStock) {
        this.entryStock = entryStock;
    }

    public String getEntryAddUid() {
        return entryAddUid;
    }

    public void setEntryAddUid(String entryAddUid) {
        this.entryAddUid = entryAddUid;
    }

    public EntryOthers getEntryOthers() {
        return entryOthers;
    }

    public void setEntryOthers(EntryOthers entryOthers) {
        this.entryOthers = entryOthers;
    }

    public String getEntryShopUrl() {
        return entryShopUrl;
    }

    public void setEntryShopUrl(String entryShopUrl) {
        this.entryShopUrl = entryShopUrl;
    }

    public EntryPriceOld getEntryPriceOld() {
        return entryPriceOld;
    }

    public void setEntryPriceOld(EntryPriceOld entryPriceOld) {
        this.entryPriceOld = entryPriceOld;
    }

}
