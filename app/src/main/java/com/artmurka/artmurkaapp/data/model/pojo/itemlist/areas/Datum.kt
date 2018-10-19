package com.artmurka.artmurkaapp.data.model.pojo.itemlist.areas

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum {

    @SerializedName("Ref")
    @Expose
    var ref: String? = null
    @SerializedName("SettlementType")
    @Expose
    var settlementType: String? = null
    @SerializedName("Latitude")
    @Expose
    var latitude: String? = null
    @SerializedName("Longitude")
    @Expose
    var longitude: String? = null
    @SerializedName("Description")
    @Expose
    var description: String? = null
    @SerializedName("DescriptionRu")
    @Expose
    var descriptionRu: String? = null
    @SerializedName("SettlementTypeDescription")
    @Expose
    var settlementTypeDescription: String? = null
    @SerializedName("SettlementTypeDescriptionRu")
    @Expose
    var settlementTypeDescriptionRu: String? = null
    @SerializedName("Region")
    @Expose
    var region: String? = null
    @SerializedName("RegionsDescription")
    @Expose
    var regionsDescription: String? = null
    @SerializedName("RegionsDescriptionRu")
    @Expose
    var regionsDescriptionRu: String? = null
    @SerializedName("Area")
    @Expose
    var area: String? = null
    @SerializedName("AreaDescription")
    @Expose
    var areaDescription: String? = null
    @SerializedName("AreaDescriptionRu")
    @Expose
    var areaDescriptionRu: String? = null
    @SerializedName("Index1")
    @Expose
    var index1: String? = null
    @SerializedName("Index2")
    @Expose
    var index2: String? = null
    @SerializedName("IndexCOATSU1")
    @Expose
    var indexCOATSU1: String? = null
    @SerializedName("Delivery1")
    @Expose
    var delivery1: String? = null
    @SerializedName("Delivery2")
    @Expose
    var delivery2: String? = null
    @SerializedName("Delivery3")
    @Expose
    var delivery3: String? = null
    @SerializedName("Delivery4")
    @Expose
    var delivery4: String? = null
    @SerializedName("Delivery5")
    @Expose
    var delivery5: String? = null
    @SerializedName("Delivery6")
    @Expose
    var delivery6: String? = null
    @SerializedName("Delivery7")
    @Expose
    var delivery7: String? = null
    @SerializedName("Warehouse")
    @Expose
    var warehouse: String? = null

}
