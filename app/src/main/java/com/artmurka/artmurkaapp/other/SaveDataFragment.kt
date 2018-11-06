package com.artmurka.artmurkaapp.other

import android.os.Bundle
import android.support.v4.app.Fragment

import com.artmurka.artmurkaapp.data.model.pojo.itemlist.categories.Success

class SaveDataFragment : Fragment() {
    var categories: List<Success>? = null
        set(list) {
            field = list
            this.childs = null
        }
    var childs: List<Success>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
}
