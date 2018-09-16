
package com.artmurka.artmurkaapp.data.model.pojo.itemlist.aboutgoods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Success {

    @SerializedName("entry_stock")
    @Expose
    private EntryStock entryStock;
    @SerializedName("entry_type")
    @Expose
    private String entryType;
    @SerializedName("entry_spec")
    @Expose
    private String entrySpec;
    @SerializedName("entry_cat")
    @Expose
    private EntryCat entryCat;
    @SerializedName("entry_description")
    @Expose
    private String entryDescription;
    @SerializedName("entry_id")
    @Expose
    private String entryId;
    @SerializedName("entry_hgu")
    @Expose
    private String entryHgu;
    @SerializedName("entry_solds")
    @Expose
    private String entrySolds;
    @SerializedName("entry_title")
    @Expose
    private String entryTitle;
    @SerializedName("entry_cats")
    @Expose
    private EntryCats entryCats;
    @SerializedName("entry_add_uid")
    @Expose
    private String entryAddUid;
    @SerializedName("entry_ordered")
    @Expose
    private String entryOrdered;
    @SerializedName("entry_rating")
    @Expose
    private EntryRating entryRating;
    @SerializedName("entry_is_hidden")
    @Expose
    private String entryIsHidden;
    @SerializedName("entry_art_no")
    @Expose
    private String entryArtNo;
    @SerializedName("entry_is_in_discount")
    @Expose
    private int entryIsInDiscount;
    @SerializedName("entry_others")
    @Expose
    private EntryOthers entryOthers;
    @SerializedName("entry_weight")
    @Expose
    private EntryWeight entryWeight;
    @SerializedName("entry_brief")
    @Expose
    private String entryBrief;

    @SerializedName("entry_meta_data")
    @Expose
    private EntryMetaData entryMetaData;
    @SerializedName("entry_file_size")
    @Expose
    private int entryFileSize;
    @SerializedName("entry_price_vat_eval")
    @Expose
    private EntryPriceVatEval entryPriceVatEval;
    @SerializedName("entry_shop_url")
    @Expose
    private String entryShopUrl;
    @SerializedName("entry_is_in_basket")
    @Expose
    private int entryIsInBasket;
    @SerializedName("entry_views")
    @Expose
    private String entryViews;
    @SerializedName("entry_unit")
    @Expose
    private String entryUnit;
    @SerializedName("entry_price_in")
    @Expose
    private EntryPriceIn entryPriceIn;
    @SerializedName("entry_warr")
    @Expose
    private String entryWarr;
    @SerializedName("entry_modified_time")
    @Expose
    private String entryModifiedTime;
    @SerializedName("entry_price_old")
    @Expose
    private EntryPriceOld entryPriceOld;
    @SerializedName("entry_options")
    @Expose
    private EntryOptions entryOptions;
    @SerializedName("entry_photo")
    @Expose
    private EntryPhoto entryPhoto;
    @SerializedName("entry_brand")
    @Expose
    private String entryBrand;
    @SerializedName("entry_add_user")
    @Expose
    private String entryAddUser;
    @SerializedName("entry_price")
    @Expose
    private EntryPrice entryPrice;
    @SerializedName("entry_is_in_wishlist")
    @Expose
    private int entryIsInWishlist;
    @SerializedName("entry_price_vat")
    @Expose
    private EntryPriceVat entryPriceVat;
    @SerializedName("entry_added_time")
    @Expose
    private String entryAddedTime;

    public Success(String others_photo, Map<String, SizePhoto> tags) {
    }

    public EntryStock getEntryStock() {
        return entryStock;
    }

    public void setEntryStock(EntryStock entryStock) {
        this.entryStock = entryStock;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public String getEntrySpec() {
        return entrySpec;
    }

    public void setEntrySpec(String entrySpec) {
        this.entrySpec = entrySpec;
    }

    public EntryCat getEntryCat() {
        return entryCat;
    }

    public void setEntryCat(EntryCat entryCat) {
        this.entryCat = entryCat;
    }

    public String getEntryDescription() {
        return entryDescription;
    }

    public void setEntryDescription(String entryDescription) {
        this.entryDescription = entryDescription;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public String getEntryHgu() {
        return entryHgu;
    }

    public void setEntryHgu(String entryHgu) {
        this.entryHgu = entryHgu;
    }

    public String getEntrySolds() {
        return entrySolds;
    }

    public void setEntrySolds(String entrySolds) {
        this.entrySolds = entrySolds;
    }

    public String getEntryTitle() {
        return entryTitle;
    }

    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }

    public EntryCats getEntryCats() {
        return entryCats;
    }

    public void setEntryCats(EntryCats entryCats) {
        this.entryCats = entryCats;
    }

    public String getEntryAddUid() {
        return entryAddUid;
    }

    public void setEntryAddUid(String entryAddUid) {
        this.entryAddUid = entryAddUid;
    }

    public String getEntryOrdered() {
        return entryOrdered;
    }

    public void setEntryOrdered(String entryOrdered) {
        this.entryOrdered = entryOrdered;
    }

    public EntryRating getEntryRating() {
        return entryRating;
    }

    public void setEntryRating(EntryRating entryRating) {
        this.entryRating = entryRating;
    }

    public String getEntryIsHidden() {
        return entryIsHidden;
    }

    public void setEntryIsHidden(String entryIsHidden) {
        this.entryIsHidden = entryIsHidden;
    }

    public String getEntryArtNo() {
        return entryArtNo;
    }

    public void setEntryArtNo(String entryArtNo) {
        this.entryArtNo = entryArtNo;
    }

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

    public EntryWeight getEntryWeight() {
        return entryWeight;
    }

    public void setEntryWeight(EntryWeight entryWeight) {
        this.entryWeight = entryWeight;
    }

    public String getEntryBrief() {
        return entryBrief;
    }

    public void setEntryBrief(String entryBrief) {
        this.entryBrief = entryBrief;
    }

    public EntryMetaData getEntryMetaData() {
        return entryMetaData;
    }

    public void setEntryMetaData(EntryMetaData entryMetaData) {
        this.entryMetaData = entryMetaData;
    }

    public int getEntryFileSize() {
        return entryFileSize;
    }

    public void setEntryFileSize(int entryFileSize) {
        this.entryFileSize = entryFileSize;
    }

    public EntryPriceVatEval getEntryPriceVatEval() {
        return entryPriceVatEval;
    }

    public void setEntryPriceVatEval(EntryPriceVatEval entryPriceVatEval) {
        this.entryPriceVatEval = entryPriceVatEval;
    }

    public String getEntryShopUrl() {
        return entryShopUrl;
    }

    public void setEntryShopUrl(String entryShopUrl) {
        this.entryShopUrl = entryShopUrl;
    }

    public int getEntryIsInBasket() {
        return entryIsInBasket;
    }

    public void setEntryIsInBasket(int entryIsInBasket) {
        this.entryIsInBasket = entryIsInBasket;
    }

    public String getEntryViews() {
        return entryViews;
    }

    public void setEntryViews(String entryViews) {
        this.entryViews = entryViews;
    }

    public String getEntryUnit() {
        return entryUnit;
    }

    public void setEntryUnit(String entryUnit) {
        this.entryUnit = entryUnit;
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

    public String getEntryModifiedTime() {
        return entryModifiedTime;
    }

    public void setEntryModifiedTime(String entryModifiedTime) {
        this.entryModifiedTime = entryModifiedTime;
    }

    public EntryPriceOld getEntryPriceOld() {
        return entryPriceOld;
    }

    public void setEntryPriceOld(EntryPriceOld entryPriceOld) {
        this.entryPriceOld = entryPriceOld;
    }

    public EntryOptions getEntryOptions() {
        return entryOptions;
    }

    public void setEntryOptions(EntryOptions entryOptions) {
        this.entryOptions = entryOptions;
    }

    public EntryPhoto getEntryPhoto() {
        return entryPhoto;
    }

    public void setEntryPhoto(EntryPhoto entryPhoto) {
        this.entryPhoto = entryPhoto;
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

    public EntryPrice getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(EntryPrice entryPrice) {
        this.entryPrice = entryPrice;
    }

    public int getEntryIsInWishlist() {
        return entryIsInWishlist;
    }

    public void setEntryIsInWishlist(int entryIsInWishlist) {
        this.entryIsInWishlist = entryIsInWishlist;
    }

    public EntryPriceVat getEntryPriceVat() {
        return entryPriceVat;
    }

    public void setEntryPriceVat(EntryPriceVat entryPriceVat) {
        this.entryPriceVat = entryPriceVat;
    }

    public String getEntryAddedTime() {
        return entryAddedTime;
    }

    public void setEntryAddedTime(String entryAddedTime) {
        this.entryAddedTime = entryAddedTime;
    }

}
