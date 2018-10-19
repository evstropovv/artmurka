package com.artmurka.artmurkaapp.data.model.pojo.itemlist.itembasket

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item {

    @SerializedName("entry_is_in_discount")
    @Expose
    var entryIsInDiscount: Int = 0
    @SerializedName("entry_others")
    @Expose
    var entryOthers: EntryOthers? = null
    @SerializedName("entry_price_in")
    @Expose
    var entryPriceIn: EntryPriceIn? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("entry_title")
    @Expose
    var entryTitle: String? = null
    @SerializedName("entry_price_vat")
    @Expose
    var entryPriceVat: EntryPriceVat? = null
    @SerializedName("entry_options")
    @Expose
    var entryOptions: String? = null
    @SerializedName("entry_add_uid")
    @Expose
    var entryAddUid: String? = null
    @SerializedName("cnt")
    @Expose
    var cnt: String? = null
    @SerializedName("entry_cat_id")
    @Expose
    var entryCatId: String? = null
    @SerializedName("entry_weight")
    @Expose
    var entryWeight: EntryWeight? = null
    @SerializedName("entry_art_no")
    @Expose
    var entryArtNo: String? = null
    @SerializedName("entry_shop_url")
    @Expose
    var entryShopUrl: String? = null
    @SerializedName("entry_unit")
    @Expose
    var entryUnit: String? = null
    @SerializedName("entry_is_in_wishlist")
    @Expose
    var entryIsInWishlist: Int = 0
    @SerializedName("entry_id")
    @Expose
    var entryId: String? = null
    @SerializedName("summ")
    @Expose
    var summ: Summ? = null
    @SerializedName("entry_photo")
    @Expose
    var entryPhoto: EntryPhoto? = null
    @SerializedName("entry_warr")
    @Expose
    var entryWarr: String? = null
    @SerializedName("over")
    @Expose
    var over: Over? = null
    @SerializedName("entry_hgu")
    @Expose
    var entryHgu: String? = null
    @SerializedName("entry_brand")
    @Expose
    var entryBrand: String? = null
    @SerializedName("entry_add_user")
    @Expose
    var entryAddUser: String? = null
    @SerializedName("entry_type")
    @Expose
    var entryType: String? = null
    @SerializedName("entry_price_vat_eval")
    @Expose
    var entryPriceVatEval: EntryPriceVatEval? = null
    @SerializedName("entry_price")
    @Expose
    var entryPrice: EntryPrice? = null

}
