package com.artmurka.artmurkaapp.Model.Modules;

import com.artmurka.artmurkaapp.Model.InterfacesModel.IOrderRequest;
import com.artmurka.artmurkaapp.Model.Pojo.ItemList.Orders.Orders;

import java.util.HashMap;

import retrofit2.Call;


public class OrdersRequests implements IOrderRequest {

    @Override
    public Call<Orders> getOrders() {
        UcozApiModule ucoz = new UcozApiModule();
        HashMap<String, String> mapForUcozModule = new HashMap<String, String>();
        mapForUcozModule.put("sort", "id");
        mapForUcozModule.put("order", "desc");
        HashMap<String, String> confForRequest = ucoz.get("GET","uapi/shop/invoices/" ,mapForUcozModule);
        return ApiModule.getClient().getInvoises(confForRequest);
    }
}
