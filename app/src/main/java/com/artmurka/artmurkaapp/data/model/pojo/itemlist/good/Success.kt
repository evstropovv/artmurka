package com.artmurka.artmurkaapp.data.model.pojo.itemlist.good

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Success {

    @SerializedName("entry_description")
    @Expose
    var entryDescription: String? = null
    @SerializedName("entry_unit")
    @Expose
    var entryUnit: String? = null
    @SerializedName("entry_art_no")
    @Expose
    var entryArtNo: String? = null
    @SerializedName("entry_type")
    @Expose
    var entryType: String? = null
    @SerializedName("entry_is_hidden")
    @Expose
    var entryIsHidden: String? = null
    @SerializedName("entry_is_in_wishlist")
    @Expose
    var entryIsInWishlist: Int? = null
    @SerializedName("entry_ordered")
    @Expose
    var entryOrdered: String? = null
    @SerializedName("entry_rating")
    @Expose
    var entryRating: EntryRating? = null
    @SerializedName("entry_weight")
    @Expose
    var entryWeight: EntryWeight? = null
    @SerializedName("entry_title")
    @Expose
    var entryTitle: String? = null
    @SerializedName("entry_is_in_basket")
    @Expose
    var entryIsInBasket: Int? = null
    @SerializedName("recommended_products")
    @Expose
    var recommendedProducts: RecommendedProducts? = null
    @SerializedName("entry_stock")
    @Expose
    var entryStock: EntryStock? = null
    @SerializedName("entry_cat")
    @Expose
    var entryCat: EntryCat? = null
    @SerializedName("entry_others")
    @Expose
    var entryOthers: EntryOthers? = null
    @SerializedName("entry_price_old")
    @Expose
    var entryPriceOld: EntryPriceOld? = null
    @SerializedName("entry_warr")
    @Expose
    var entryWarr: String? = null
    @SerializedName("entry_options")
    @Expose
    var entryOptions: EntryOptions? = null
    @SerializedName("entry_brand")
    @Expose
    var entryBrand: String? = null
    @SerializedName("entry_solds")
    @Expose
    var entrySolds: String? = null
    @SerializedName("entry_modified_time")
    @Expose
    var entryModifiedTime: String? = null
    @SerializedName("entry_meta_data")
    @Expose
    var entryMetaData: EntryMetaData? = null
    @SerializedName("entry_shop_url")
    @Expose
    var entryShopUrl: String? = null
    @SerializedName("entry_views")
    @Expose
    var entryViews: String? = null
    @SerializedName("entry_price_vat_eval")
    @Expose
    var entryPriceVatEval: EntryPriceVatEval? = null
    @SerializedName("entry_added_time")
    @Expose
    var entryAddedTime: String? = null
    @SerializedName("entry_add_user")
    @Expose
    var entryAddUser: String? = null
    @SerializedName("entry_spec")
    @Expose
    var entrySpec: String? = null
    @SerializedName("entry_id")
    @Expose
    var entryId: String? = null
    @SerializedName("entry_cats")
    @Expose
    var entryCats: EntryCats? = null
    @SerializedName("entry_price")
    @Expose
    var entryPrice: EntryPrice? = null
    @SerializedName("entry_brief")
    @Expose
    var entryBrief: String? = null
    @SerializedName("entry_add_uid")
    @Expose
    var entryAddUid: String? = null
    @SerializedName("entry_price_vat")
    @Expose
    var entryPriceVat: EntryPriceVat? = null
    @SerializedName("entry_hgu")
    @Expose
    var entryHgu: String? = null
    @SerializedName("entry_price_in")
    @Expose
    var entryPriceIn: EntryPriceIn? = null
    @SerializedName("entry_photo")
    @Expose
    var entryPhoto: EntryPhoto? = null
    @SerializedName("entry_is_in_discount")
    @Expose
    var entryIsInDiscount: Int? = null
    @SerializedName("entry_file_size")
    @Expose
    var entryFileSize: Int? = null

}
