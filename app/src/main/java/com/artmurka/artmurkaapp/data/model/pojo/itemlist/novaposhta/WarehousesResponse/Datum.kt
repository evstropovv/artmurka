package com.artmurka.artmurkaapp.data.model.pojo.itemlist.novaposhta.WarehousesResponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum {
    @SerializedName("Number")
    @Expose
    var number: String? = null

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

    fun withRef(ref: String): Datum {
        this.ref = ref
        return this
    }

    fun withSettlementType(settlementType: String): Datum {
        this.settlementType = settlementType
        return this
    }

    fun withLatitude(latitude: String): Datum {
        this.latitude = latitude
        return this
    }

    fun withLongitude(longitude: String): Datum {
        this.longitude = longitude
        return this
    }

    fun withDescription(description: String): Datum {
        this.description = description
        return this
    }

    fun withDescriptionRu(descriptionRu: String): Datum {
        this.descriptionRu = descriptionRu
        return this
    }

    fun withSettlementTypeDescription(settlementTypeDescription: String): Datum {
        this.settlementTypeDescription = settlementTypeDescription
        return this
    }

    fun withSettlementTypeDescriptionRu(settlementTypeDescriptionRu: String): Datum {
        this.settlementTypeDescriptionRu = settlementTypeDescriptionRu
        return this
    }

    fun withRegion(region: String): Datum {
        this.region = region
        return this
    }

    fun withRegionsDescription(regionsDescription: String): Datum {
        this.regionsDescription = regionsDescription
        return this
    }

    fun withRegionsDescriptionRu(regionsDescriptionRu: String): Datum {
        this.regionsDescriptionRu = regionsDescriptionRu
        return this
    }

    fun withArea(area: String): Datum {
        this.area = area
        return this
    }

    fun withAreaDescription(areaDescription: String): Datum {
        this.areaDescription = areaDescription
        return this
    }

    fun withAreaDescriptionRu(areaDescriptionRu: String): Datum {
        this.areaDescriptionRu = areaDescriptionRu
        return this
    }

    fun withIndex1(index1: String): Datum {
        this.index1 = index1
        return this
    }

    fun withIndex2(index2: String): Datum {
        this.index2 = index2
        return this
    }

    fun withIndexCOATSU1(indexCOATSU1: String): Datum {
        this.indexCOATSU1 = indexCOATSU1
        return this
    }

    fun withDelivery1(delivery1: String): Datum {
        this.delivery1 = delivery1
        return this
    }

    fun withDelivery2(delivery2: String): Datum {
        this.delivery2 = delivery2
        return this
    }

    fun withDelivery3(delivery3: String): Datum {
        this.delivery3 = delivery3
        return this
    }

    fun withDelivery4(delivery4: String): Datum {
        this.delivery4 = delivery4
        return this
    }

    fun withDelivery5(delivery5: String): Datum {
        this.delivery5 = delivery5
        return this
    }

    fun withDelivery6(delivery6: String): Datum {
        this.delivery6 = delivery6
        return this
    }

    fun withDelivery7(delivery7: String): Datum {
        this.delivery7 = delivery7
        return this
    }


    fun withWarehouse(warehouse: String): Datum {
        this.warehouse = warehouse
        return this
    }

}
