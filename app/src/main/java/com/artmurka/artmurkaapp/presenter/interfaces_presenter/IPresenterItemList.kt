package com.artmurka.artmurkaapp.presenter.interfaces_presenter


interface IPresenterItemList {
        fun getCategoriesData(curPage: Int,  url: String, sort: String, order: String);
}
