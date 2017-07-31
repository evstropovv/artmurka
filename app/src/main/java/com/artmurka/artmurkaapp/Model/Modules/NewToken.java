package com.artmurka.artmurkaapp.Model.Modules;


import com.artmurka.artmurkaapp.Model.InterfacesModel.INewToken;

import java.util.HashMap;

import retrofit2.Call;

public class NewToken implements INewToken {

    @Override
    public Call<String> oAuthGetRequestToken() {
        UcozApiModule ucoz = new UcozApiModule();

        //Getting all token for autorization.
//        HashMap<String, String> confForRequest = ucoz.get("GET","uapi/shop/request" , mapForUcozModule);
//        confForRequest.put("page", mapForUcozModule.get("page"));
//
//        return ApiModule.getClient().oAuthGetRequestToken(confForRequest);
        return null;
    }
}
