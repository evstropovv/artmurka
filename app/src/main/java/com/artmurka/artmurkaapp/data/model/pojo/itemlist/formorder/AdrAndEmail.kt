package com.artmurka.artmurkaapp.data.model.pojo.itemlist.formorder

//Необходим при заказе
//для Полей емейл и адрес;

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AdrAndEmail(@field:SerializedName("fld2")
                  @field:Expose
                  internal var fld2: String, @field:SerializedName("fld3")
                  @field:Expose
                  internal var fld3: String)
